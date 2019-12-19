package com.example.buquduo.User;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.buquduo.Base.MyTool;
import com.example.buquduo.Network.BQDHttpTool;
import com.example.buquduo.Network.MyBaseCallBack;
import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

public class HealthActivity extends AppCompatActivity {

    Button ensurebtn;
    EditText heightTxt;
    EditText weightTxt;
    EditText ageTxt;
    EditText stepTxt;
    EditText genderTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        initView();

        setback();
    }

    TitleBar titleBar;
    public void setback() {
        titleBar = findViewById(R.id.healthtitlebar);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                HealthActivity.this.finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }

    public void initView() {
        ensurebtn = findViewById(R.id.heal_confirm_btn);
        heightTxt = findViewById(R.id.health_height);
        weightTxt = findViewById(R.id.health_weight);
        ageTxt = findViewById(R.id.heal_age);
        genderTxt = findViewById(R.id.healsex);
        stepTxt = findViewById(R.id.heal_step);


        ensurebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotosave();
            }
        });
    }

    private void gotosave() {
        if (TextUtils.isEmpty(heightTxt.getText())) {
            MyTool.makeToast(this,"请输入身高");
            return;
        }
        if (TextUtils.isEmpty(weightTxt.getText())) {
            MyTool.makeToast(this,"请输入体重");
            return;
        }

        if (TextUtils.isEmpty(ageTxt.getText())) {
            MyTool.makeToast(this,"请输入年龄");
            return;
        }
        if (TextUtils.isEmpty(genderTxt.getText())) {
            MyTool.makeToast(this,"请输入性别");
            return;
        }
        if (TextUtils.isEmpty(stepTxt.getText())) {
            MyTool.makeToast(this,"请输入目标步数");
            return;
        }


        HashMap hashMap = new HashMap();
        hashMap.put("height",heightTxt.getText().toString());
        hashMap.put("weight",heightTxt.getText().toString());
        hashMap.put("target_step",stepTxt.getText().toString());
        hashMap.put("age",ageTxt.getText().toString());

        if (ageTxt.getText().toString() == "男") {
            hashMap.put("gender","1");
        }else {
            hashMap.put("gender","0");
        }


        BQDHttpTool.getShareInstance().post("api/user/updateUserInfo",hashMap, new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(HealthActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                MyTool.makeToast(HealthActivity.this,"保存身体资料成功");
                HealthActivity.this.finish();
            }
        });
    }


}