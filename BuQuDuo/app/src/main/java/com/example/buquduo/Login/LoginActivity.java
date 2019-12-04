package com.example.buquduo.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;

import androidx.appcompat.app.AppCompatActivity;



public class LoginActivity extends AppCompatActivity {

    Button sendcodeBtn;
    Button loginBtn;
    EditText phoneTxt;
    EditText codeTxt;


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
    }

    public void gotosend() {
        Toast.makeText(this,"发送验证码",Toast.LENGTH_SHORT).show();
    }


}