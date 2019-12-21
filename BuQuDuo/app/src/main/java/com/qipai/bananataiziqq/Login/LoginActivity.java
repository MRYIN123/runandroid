package com.qipai.bananataiziqq.Login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qipai.bananataiziqq.Base.MyTool;
import com.qipai.bananataiziqq.Base.ResReturnItem;
import com.qipai.bananataiziqq.Network.BQDHttpTool;
import com.qipai.bananataiziqq.Network.MyBaseCallBack;
import com.qipai.bananataiziqq.R;
import com.qipai.bananataiziqq.bar.OnTitleBarListener;
import com.qipai.bananataiziqq.bar.TitleBar;
import com.google.gson.Gson;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;


public class LoginActivity extends AppCompatActivity {

    Button sendcodeBtn;
    Button loginBtn;
    EditText phoneTxt;
    EditText codeTxt;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initview();

        setmyTitleBar();
    }

    TitleBar titleBar;
    public void setmyTitleBar(){
        titleBar = findViewById(R.id.logintitlebar);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                LoginActivity.this.finish();
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
        loginBtn = findViewById(R.id.login_btn);
        sendcodeBtn = findViewById(R.id.login_send);
        codeTxt = findViewById(R.id.login_codetxt);
        phoneTxt = findViewById(R.id.login_phonetxt);


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
        if (phoneTxt.getText() == null || phoneTxt.getText().length() != 11){
            Toast.makeText(this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
            return;
        }

        if (codeTxt.getText() == null || codeTxt.getText().length() < 4){
            Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }


        HashMap hashMap = new HashMap();
        hashMap.put("phone",Integer.valueOf(phoneTxt.getText().toString()).intValue());
        hashMap.put("code",Integer.valueOf(codeTxt.getText().toString()).intValue());

        String url = "api/user/login";
        BQDHttpTool.getShareInstance().post(url,hashMap, new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(LoginActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                Gson gson = new Gson();

                Customer customer = gson.fromJson(gson.toJson(((ResReturnItem)response).data),Customer.class);
                Log.d("login登录之后","getToken_type" + customer.getToken_type() + "token=" + customer.getAccess_token());

                //保存到本地
                BQDHttpTool.getShareInstance().setAccess_token(customer.getAccess_token());
                BQDHttpTool.getShareInstance().setToken_type(customer.getToken_type());

                //个人数据
                getUserInfo();
            }
        });

    }

    public void getUserInfo() {
        String url =  "api/user/getUserInfo";
        BQDHttpTool.getShareInstance().get(url, new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(LoginActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                Gson gson = new Gson();

                Customer customer = gson.fromJson(gson.toJson(((ResReturnItem)response).data),Customer.class);

                //存储
                Customer.saveCustomer(LoginActivity.this,customer);

                //结束跳转
                LoginActivity.this.finish();
            }
        });

    }

    public void gotosend() {
        Toast.makeText(this,"发送验证码",Toast.LENGTH_SHORT).show();
        if (phoneTxt.getText() == null || phoneTxt.getText().length() != 11){
            Toast.makeText(this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
            return;
        }


        String url =  "api/SendMessage/" + phoneTxt.getText();
        BQDHttpTool.getShareInstance().get(url, new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(LoginActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                MyTool.makeToast(LoginActivity.this,"发送验证码成功");


                timer.start();
                setupCodeBtnUI(false,60000);
            }
        });


        timer.start();
    }

}