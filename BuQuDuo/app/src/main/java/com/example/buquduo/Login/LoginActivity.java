package com.example.buquduo.Login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buquduo.Base.ResReturnItem;
import com.example.buquduo.Network.WBHttpUtils;
import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;
import com.google.gson.Gson;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Response;


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
        Toast.makeText(this,"登录",Toast.LENGTH_SHORT).show();
        if (phoneTxt.getText() == null || phoneTxt.getText().length() != 11){
            Toast.makeText(this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
            return;
        }

        if (codeTxt.getText() == null || codeTxt.getText().length() < 4){
            Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }


        String url = getResources().getString(R.string.url_base) + "api/user/login" + phoneTxt.getText();
        WBHttpUtils.getShareInstance().getDataAsyn(url, new WBHttpUtils.WBNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {

                String resStr = response.body().string();
                Gson gson = new Gson();
                ResReturnItem item = gson.fromJson(resStr,ResReturnItem.class);


                Customer customer = gson.fromJson(gson.toJson(item.data),Customer.class);
                Log.d("login登录之后","getToken_type" + customer.getToken_type() + "token=" + customer.getAccess_token());

                //保存到本地
                WBHttpUtils.getShareInstance().setAccess_token(customer.getAccess_token());
                WBHttpUtils.getShareInstance().setToken_type(customer.getToken_type());

                //个人数据
                getUserInfo();
            }

            @Override
            public void failed(Call call, IOException e) {
                makeToast(e.getMessage());
            }
        });
    }

    public void getUserInfo() {
        String url = getResources().getString(R.string.url_base) +  "api/user/getUserInfo";
        WBHttpUtils.getShareInstance().getDataAsyn(url, new WBHttpUtils.WBNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {


                String resStr = response.body().string();
                Gson gson = new Gson();
                ResReturnItem item = gson.fromJson(resStr,ResReturnItem.class);

                Customer customer = gson.fromJson(gson.toJson(item.data),Customer.class);

                //存储
                Customer.saveCustomer(LoginActivity.this,customer);

                //结束跳转
                LoginActivity.this.finish();
            }

            @Override
            public void failed(Call call, IOException e) {
                makeToast(e.getMessage());
            }
        });
    }

    public void gotosend() {
        Toast.makeText(this,"发送验证码",Toast.LENGTH_SHORT).show();
        if (phoneTxt.getText() == null || phoneTxt.getText().length() != 11){
            Toast.makeText(this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
            return;
        }


        String url = getResources().getString(R.string.url_base) + "api/SendMessage/" + phoneTxt.getText();
        WBHttpUtils.getShareInstance().getDataAsyn(url, new WBHttpUtils.WBNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                makeToast("发送验证码成功");

                timer.start();
                setupCodeBtnUI(false,60000);
            }

            @Override
            public void failed(Call call, IOException e) {
                makeToast(e.getMessage());
            }
        });
        timer.start();
    }

    public void makeToast(String txt){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(getBaseContext(),txt,Toast.LENGTH_SHORT).show();
            }
        });
    }

}