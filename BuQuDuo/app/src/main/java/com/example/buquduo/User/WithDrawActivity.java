package com.example.buquduo.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;

public class WithDrawActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw);

        configTitlebar();

        initview();

        initdata();

    }

    TitleBar titleBar;
    public void configTitlebar() {
        titleBar = findViewById(R.id.titlebar_withdraw);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                WithDrawActivity.this.finish();
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

    }

    public void initdata() {

    }





}
