package com.qipai.bananataiziqq.User;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qipai.bananataiziqq.Base.MyTool;
import com.qipai.bananataiziqq.Login.Customer;
import com.qipai.bananataiziqq.Network.BQDHttpTool;
import com.qipai.bananataiziqq.Network.MyBaseCallBack;
import com.qipai.bananataiziqq.R;
import com.qipai.bananataiziqq.bar.OnTitleBarListener;
import com.qipai.bananataiziqq.bar.TitleBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.HashMap;

public class BindAccountActivity extends AppCompatActivity {


    Button ensureBtn;
    Button codeBtn;
    EditText phoneTxt;
    EditText codeTxt;
    TextView bindTxt;
    TextView bindTitleTxt;
    TitleBar titleBar;
    HasBindType hasBindType = HasBindType.All;
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_account);

        initData();

        initView();

        setupTitleBar();

        updateUI();
    }

    public void setupTitleBar() {
        titleBar = findViewById(R.id.bindTitleBar);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                BindAccountActivity.this.finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }

    public void initData() {
        Customer customer = Customer.getCustomer(this);
         if (customer.getIsWechat() == 1 && !TextUtils.isEmpty(customer.getPhone())) {
             hasBindType = HasBindType.All;

         }else if (customer.getIsWechat() == 0 && !TextUtils.isEmpty(customer.getPhone())){
             hasBindType = HasBindType.Phone;

         }else if (customer.getIsWechat() == 1 && TextUtils.isEmpty(customer.getPhone())){
             hasBindType = HasBindType.WeChat;
         };

    }

    public void initView() {
        ensureBtn = findViewById(R.id.bindbtn);
        codeBtn = findViewById(R.id.codebtn);
        phoneTxt = findViewById(R.id.bind_phonetxt);
        codeTxt = findViewById(R.id.bind_codetxt);
        bindTitleTxt = findViewById(R.id.bindtxt);
        bindTxt = findViewById(R.id.bindInfotxt);
        codeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCode();
            }
        });

        ensureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ensureAction();
            }
        });

    }

    public enum HasBindType {
        All,
        Phone,
        WeChat
    }

    public void updateUI() {
        if (hasBindType == HasBindType.All){
            bindTxt.setText("您已经全部绑定");
            ensureBtn.setEnabled(false);
            ensureBtn.setBackgroundColor(getResources().getColor(R.color.color999999));
            phoneTxt.setVisibility(View.INVISIBLE);
            codeTxt.setVisibility(View.INVISIBLE);
            codeBtn.setVisibility(View.INVISIBLE);

        }else if (hasBindType == HasBindType.Phone) {
            bindTxt.setText("请绑定微信");
            phoneTxt.setVisibility(View.INVISIBLE);
            codeTxt.setVisibility(View.INVISIBLE);
            codeBtn.setVisibility(View.INVISIBLE);
            LinearLayout.LayoutParams ll_params = (LinearLayout.LayoutParams) ensureBtn.getLayoutParams();
            ll_params.setMargins(10,30,10,10);
            ensureBtn.setLayoutParams(ll_params);

        }else if (hasBindType == HasBindType.WeChat) {
            bindTxt.setText("请绑定手机");
            timer = new CountDownTimer(60000,1000) {
                @Override
                public void onTick(long l) {
                    setupCodeUI(false,l / 1000);
                }

                @Override
                public void onFinish() {
                    setupCodeUI(true,0);
                }
            };

        }
    }




    public void clickCode() {
        Toast.makeText(this,"code",Toast.LENGTH_SHORT).show();
        if (phoneTxt.getText() == null || phoneTxt.getText().length() != 11) {
            Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            return;
        }


        //验证码发送
        String url =  "api/SendMessage/" + phoneTxt.getText();
        BQDHttpTool.getShareInstance().get(url, new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(BindAccountActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                MyTool.makeToast(BindAccountActivity.this,"验证码已发送");

                timer.start();
                setupCodeUI(false,60000);
            }
        });


    }

    //倒计时，ui显示
    public void setupCodeUI(Boolean finish,long time) {
        if (finish){
            codeBtn.setEnabled(true);
            codeBtn.setText("验证码");

        }else {
            codeBtn.setText(String.valueOf(time) + "s");
            codeBtn.setEnabled(false);
        }

    }



    public void ensureAction() {
        Toast.makeText(this,"保存",Toast.LENGTH_SHORT).show();

        if (hasBindType == HasBindType.WeChat) {
            bindPhone();

        }else if (hasBindType == HasBindType.Phone) {
            bindWx();
        }

    }


    public void bindPhone() {
        if (phoneTxt.getText() == null || phoneTxt.getText().length()  != 11) {
            Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            return;
        }

        if (codeTxt.getText() == null || codeTxt.getText().length() == 0) {
            Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }

        String url =  "api/user/bookbindingPhone";
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("phone",phoneTxt.getText().toString());
        params.put("code",codeTxt.getText().toString());


        OkHttpUtils.post().url(url).
                addParams("phone",phoneTxt.getText().toString()).
                addParams("code",codeTxt.getText().toString()).build().execute(new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(BindAccountActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Bitmap response, int id) {
                finishActivity();
            }
        });
    }

    //跳转会上个界面
    public void finishActivity() {
        BindAccountActivity.this.finish();
    }


    public void bindWx() {
        String url =  "api/user/bookbindingWechat";
        HashMap<String,String>hashMap = new HashMap<String, String>();
        String code = "微信登录返回的code";
        hashMap.put("code",code);

        OkHttpUtils.post().url(url)
                .addParams("code",code)
                .build().execute(new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(BindAccountActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                finishActivity();
            }
        });

    }





}
