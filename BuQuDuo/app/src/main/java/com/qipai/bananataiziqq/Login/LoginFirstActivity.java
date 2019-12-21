package com.qipai.bananataiziqq.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.qipai.bananataiziqq.Base.MyTool;
import com.qipai.bananataiziqq.MainActivity;
import com.qipai.bananataiziqq.R;
import com.qipai.bananataiziqq.bar.OnTitleBarListener;
import com.qipai.bananataiziqq.bar.TitleBar;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class LoginFirstActivity extends AppCompatActivity  {

    Button phoneloginbtn;
    Button loginBtn;

    private static IWXAPI WXapi;
    private String WX_APP_ID = "wxb52a6345ac6991f8";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_first);


        initview();

        setmyTitleBar();

        registWx();

        // 隐藏状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        //接收到分享以及登录的intent传递handleIntent方法，处理结果
//        WXapi = WXAPIFactory.createWXAPI(this, "wx45ccf8958a0a24c7", false);
//        WXapi.handleIntent(getIntent(), this);

    }

//    @Override
//    public void onReq(BaseReq baseReq) {
//
//    }
//
//    @Override
//    public void onResp(BaseResp baseResp) {
//        //登录回调
//        switch (baseResp.errCode) {
//            case BaseResp.ErrCode.ERR_OK:
//                String code = ((SendAuth.Resp) baseResp).code;
//                Log.d("微信登录返回", code.toString()+ "");
//                MyTool.makeToast(LoginFirstActivity.this,code.toString());
//
//                break;
//            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
//                finish();
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
//                finish();
//                break;
//            default:
//                finish();
//                break;
//        }
//    }


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
                wxLogin();
            }
        });

        phoneloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginFirstActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    public void registWx() {
        WXapi = WXAPIFactory.createWXAPI(this, WX_APP_ID, true);
        WXapi.registerApp(WX_APP_ID);
    }

    public void wxLogin() {
        if (!WXapi.isWXAppInstalled()) {
            MyTool.makeToast(LoginFirstActivity.this,"您的设备未安装微信客户端");

        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            WXapi.sendReq(req);
        }
    }



}
