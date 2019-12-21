package com.qipai.bananataiziqq.wxapi;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.qipai.bananataiziqq.Base.MyTool;
import com.qipai.bananataiziqq.Base.ResReturnItem;
import com.qipai.bananataiziqq.Login.Customer;
import com.qipai.bananataiziqq.Login.MyDefaultItem;
import com.qipai.bananataiziqq.Network.BQDHttpTool;
import com.qipai.bananataiziqq.Network.MyBaseCallBack;
import com.google.gson.Gson;
import com.qipai.bananataiziqq.R;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {


    private IWXAPI api;
    private BaseResp resp = null;
    private String WX_APP_ID = "wxb52a6345ac6991f8";
    private String WX_APP_SECRET = "906636a7c539b4f7b877e5ae3151b1a1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wxentry);


        api = WXAPIFactory.createWXAPI(this, WX_APP_ID, false);
        api.handleIntent(getIntent(), this);

    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
        finish();
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        String result = "";
        if (resp != null) {
            resp = resp;
        }
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "发送成功";
                MyTool.makeToast(WXEntryActivity.this,result);

                String code = ((SendAuth.Resp) resp).code;
                gotologinWX(code);

                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                MyTool.makeToast(WXEntryActivity.this,result);
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                MyTool.makeToast(WXEntryActivity.this,result);

                finish();
                break;
            default:
                result = "发送返回";
                MyTool.makeToast(WXEntryActivity.this,result);

                finish();
                break;
        }
    }

    public void gotoAction(String code) {
        MyDefaultItem item = MyDefaultItem.getMyDefaultItem(WXEntryActivity.this);


        if (item.getBindflag() == "1") {
            gotoBindWx(code);

        }else {
            gotologinWX(code);
        }
    }

    public void gotologinWX(String code) {
        HashMap hashMap = new HashMap();
        hashMap.put("code",code);

        BQDHttpTool.getShareInstance().post("api/user/login/wechat",hashMap, new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(WXEntryActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {

                Gson gson = new Gson();

                Customer customer = gson.fromJson(gson.toJson(((ResReturnItem)response).data),Customer.class);
                Log.d("login登录之后","getToken_type" + customer.getToken_type() + "token=" + customer.getAccess_token());

                //保存到本地
                BQDHttpTool.getShareInstance().setAccess_token(customer.getAccess_token());
                BQDHttpTool.getShareInstance().setToken_type(customer.getToken_type());

                getUserInfo();
            }
        });
    }


    public void gotoBindWx(String code) {
        HashMap hashMap = new HashMap();
        hashMap.put("code",code);

        BQDHttpTool.getShareInstance().post("api/user/bookbindingWechat",hashMap, new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(WXEntryActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {

            }
        });
    }



    /**
     * 通过拼接的用户信息url获取用户信息
     *

     */
    private void getUserInfo() {
        BQDHttpTool.getShareInstance().get("api/user/getUserInfo", new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(WXEntryActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                Gson gson = new Gson();
                Customer customer = gson.fromJson(gson.toJson(((ResReturnItem)response).data),Customer.class);

                //存储
                Customer.saveCustomer(WXEntryActivity.this,customer);

                //结束跳转
                WXEntryActivity.this.finish();
            }
        });


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);

        finish();
    }


}
