package com.example.buquduo.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;

public class WithDrawActivity extends AppCompatActivity {


    Button withdrawBtn;
    Button withdrawRecordBtn;
    TextView amountTxt;
    TextView wxname;
    TextView needCoinTxt;
    ImageView wxImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw);

        configTitlebar();

        initview();

        initdata();

        updateUI();
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
        wxname = findViewById(R.id.withdraw_wx_name);
        wxImg = findViewById(R.id.withdraw_wx_img);
        withdrawRecordBtn = findViewById(R.id.withdraw_recrodbtn);
        withdrawBtn = findViewById(R.id.widthdraw_btn);
        amountTxt = findViewById(R.id.withdraw_amount);
        needCoinTxt = findViewById(R.id.withdrawcountTxt);

        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(WithDrawActivity.this,WithDrawConfirmActivity.class);
                startActivity(intent);
            }
        });

        withdrawRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(WithDrawActivity.this,WithDrawListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initdata() {

    }

    private void updateUI() {

    }





}
