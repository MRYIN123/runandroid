package com.example.buquduo.User;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

import android.content.Intent;
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
import com.example.buquduo.Network.MyBaseCallBack;
import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

public class BindBankActivity extends AppCompatActivity {


    Button selectBankBtn;
    Button ensureBtn;
    EditText cardNoTxt;
    BankItem selectItem = new BankItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_bank);

        initview();

        setmyTitleBar();
    }

    TitleBar titleBar;
    public void setmyTitleBar(){
        titleBar = findViewById(R.id.logintitlebar);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                BindBankActivity.this.finish();
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
        selectBankBtn = findViewById(R.id.bindbank_selectcard);
        ensureBtn = findViewById(R.id.bindbank_confirm_btn);
        cardNoTxt = findViewById(R.id.bindbank_cardno);

        ensureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ensureaction();
            }
        });

        selectBankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectbank();
            }
        });


    }


    public void ensureaction() {
        if (cardNoTxt.getText() == null || cardNoTxt.getText().length() != 6){
            Toast.makeText(this,"请输入卡号",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(selectItem.bank_name)) {
            Toast.makeText(this,"请选择银行",Toast.LENGTH_SHORT).show();
            return;
        }
        String bank_id = selectItem.id;


        String url = getResources().getString(R.string.url_base) + "api/user/bank";
        OkHttpUtils.post().url(url).addParams("bank_id",bank_id).addParams("cardNo",cardNoTxt.getText().toString()).build().execute(new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(BindBankActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                Gson gson = new Gson();

                Customer customer = gson.fromJson(gson.toJson(((ResReturnItem)response).data),Customer.class);
                Log.d("login登录之后","getToken_type" + customer.getToken_type() + "token=" + customer.getAccess_token());


                BindBankActivity.this.finish();

            }
        });
    }


    public void selectbank() {
        Intent intent = new Intent();
        intent.setClass(this,BindBankActivity.class);
        startActivity(intent);
    }


}
