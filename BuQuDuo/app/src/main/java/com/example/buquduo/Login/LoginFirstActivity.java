package com.example.buquduo.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.example.buquduo.Base.MyTool;
import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;

public class LoginFirstActivity extends AppCompatActivity {

    Button phoneloginbtn;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_first);


        initview();

        setmyTitleBar();
    }

    TitleBar titleBar;
    public void setmyTitleBar(){
        titleBar = findViewById(R.id.loginfirstTitleBar);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                LoginFirstActivity.this.finish();
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
        loginBtn = findViewById(R.id.wxloginbtn);
        phoneloginbtn = findViewById(R.id.phoneloginbtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginFirstActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        phoneloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxLogin();
            }
        });


    }

    public void wxLogin() {
        MyTool.makeToast(this,"微信登录");
    }



}
