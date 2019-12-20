package com.example.buquduo.User;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buquduo.Base.MyTool;
import com.example.buquduo.Base.ResReturnItem;
import com.example.buquduo.Login.Customer;
import com.example.buquduo.Login.LoginActivity;
import com.example.buquduo.Network.BQDHttpTool;
import com.example.buquduo.Network.MyBaseCallBack;
import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

public class SetPayPwdActivity extends AppCompatActivity {

    Button sendcodeBtn;
    Button loginBtn;
    EditText pwdTxt;
    EditText codeTxt;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pay_pwd);

        initview();

        setmyTitleBar();
    }

    TitleBar titleBar;
    public void setmyTitleBar(){
        titleBar = findViewById(R.id.titlebar_setpaypwd);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                SetPayPwdActivity.this.finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }


    public void initview() {
        loginBtn = findViewById(R.id.setpaypwd_btn);
        sendcodeBtn = findViewById(R.id.setpaypwd_btn);
        codeTxt = findViewById(R.id.setpaypwd_codetxt);
        pwdTxt = findViewById(R.id.setpaypwd_pwdtxt);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });

        sendcodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });

        timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                setupCodeBtnUI(false,l);
            }

            @Override
            public void onFinish() {
                setupCodeBtnUI(true,60000);
            }
        };
    }

    public void setupCodeBtnUI(Boolean finish,long time){
        if (finish) {
            sendcodeBtn.setEnabled(true);
            sendcodeBtn.setText("验证码");

        }else {
            sendcodeBtn.setText( String.valueOf(time) + "s");
            sendcodeBtn.setEnabled(false);

        }
    }

    public void click(View v){
        switch (v.getId()) {
            case R.id.login_btn:
                gotologoin();
                break;
            case R.id.login_send:
                gotosend();
                break;
            default:break;
        }
    }

    public void gotologoin() {
        if (pwdTxt.getText() == null || pwdTxt.getText().length() != 6){
            Toast.makeText(this,"请输入6位支付密码",Toast.LENGTH_SHORT).show();
            return;
        }

        if (codeTxt.getText() == null || codeTxt.getText().length() < 4){
            Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }


        String url = "api/user/setPayPassword" + pwdTxt.getText();
        OkHttpUtils.post().url(url).addParams("pay_password",pwdTxt.getText().toString()).addParams("code",codeTxt.getText().toString()).build().execute(new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(SetPayPwdActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                Gson gson = new Gson();

                Customer customer = gson.fromJson(gson.toJson(((ResReturnItem)response).data),Customer.class);
                Log.d("login登录之后","getToken_type" + customer.getToken_type() + "token=" + customer.getAccess_token());


                SetPayPwdActivity.this.finish();

            }
        });
    }


    public void gotosend() {
        Customer customer = Customer.getCustomer(this);
        if (TextUtils.isEmpty(customer.getPhone())){
            MyTool.makeToast(this,"电话号码为空");
            return;
        }

        String url = "api/SendMessage/" + customer.getPhone();
        BQDHttpTool.getShareInstance().get(url, new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(SetPayPwdActivity.this,e.getMessage());

            }

            @Override
            public void onResponse(Object response, int id) {
                MyTool.makeToast(SetPayPwdActivity.this,"发送验证码成功");


                timer.start();
                setupCodeBtnUI(false,60000);
            }
        });


        timer.start();
    }


}
