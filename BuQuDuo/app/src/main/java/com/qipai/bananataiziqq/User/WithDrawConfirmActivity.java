package com.qipai.bananataiziqq.User;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qipai.bananataiziqq.Base.MyTool;
import com.qipai.bananataiziqq.Network.BQDHttpTool;
import com.qipai.bananataiziqq.Network.MyBaseCallBack;
import com.qipai.bananataiziqq.R;
import com.qipai.bananataiziqq.bar.OnTitleBarListener;
import com.qipai.bananataiziqq.bar.TitleBar;

public class WithDrawConfirmActivity extends AppCompatActivity {


    Button ensureBtn;
    Button selectBankBtn;
    EditText cardNoTxt;
    EditText nameTxt;
    TextView eduTxt;
    EditText countTxt;
    EditText pwdTxt;
    TextView bankTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw_confirm);


        initview();

        initdata();

        updateui();

        setnavbar();
    }

    private void setnavbar() {
        ((TitleBar)findViewById(R.id.titlebar_withdrawconfirm)).setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                WithDrawConfirmActivity.this.finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }

    private void initview() {
        ensureBtn = findViewById(R.id.withdraw_confirm_btn);
        selectBankBtn = findViewById(R.id.dc_selectcard);
        nameTxt = findViewById(R.id.dc_name);
        countTxt = findViewById(R.id.dc_count);
        cardNoTxt = findViewById(R.id.dc_cardno);
        eduTxt = findViewById(R.id.dc_edu);
        pwdTxt = findViewById(R.id.dc_paypwd);

        ensureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apply();
            }
        });

        selectBankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(WithDrawConfirmActivity.this,BankListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initdata() {

    }

    private void updateui(){

    }

    private void apply() {
        if (TextUtils.isEmpty(nameTxt.getText())) {
            MyTool.makeToast(this,"请输入名字");
            return;
        }
        if (TextUtils.isEmpty(cardNoTxt.getText())) {
            MyTool.makeToast(this,"请输入卡号");
            return;
        }

        if (TextUtils.isEmpty(countTxt.getText())) {
            MyTool.makeToast(this,"请输入提现金额");
            return;
        }
//        小于提现额度
//        if (TextUtils.isEmpty(nameTxt.getText())) {
//            MyTool.makeToast(this,"请输入名字");
//            return;
//        }
//        银行不为空
//        if (TextUtils.isEmpty(nameTxt.getText())) {
//            MyTool.makeToast(this,"请输入名字");
//            return;
//        }
        if (TextUtils.isEmpty(pwdTxt.getText())) {
            MyTool.makeToast(this,"请输入支付密码");
            return;
        }

        BQDHttpTool.getShareInstance().get("api/withdraw", new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(WithDrawConfirmActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                MyTool.makeToast(WithDrawConfirmActivity.this,"提现申请成功");
                WithDrawConfirmActivity.this.finish();
            }
        });
    }



}
