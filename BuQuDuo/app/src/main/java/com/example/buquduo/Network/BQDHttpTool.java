package com.example.buquduo.Network;

import android.text.TextUtils;

import com.example.buquduo.R;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;
import com.example.buquduo.R;

public class BQDHttpTool {

    private static final byte[] LOCKER = new byte[0];
    private static BQDHttpTool shareInstance;

    private String BaseUrl = "http://58482.top/";
    private String Access_token = "";
    private String Token_type = "";


    private BQDHttpTool() {


    }

    /*
        单例模式获取WBHttpClient
         */
    public static BQDHttpTool getShareInstance(){
        if (shareInstance == null) {
            synchronized (LOCKER) {
                if (shareInstance == null) {
                    shareInstance = new BQDHttpTool();
                }
            }
        }



        return shareInstance;
    }

    //header内容
    public void setToken_type(String token_type) {
        Token_type = token_type;
    }

    public void setAccess_token(String access_token) {
        Access_token = access_token;
    }



    public HashMap header() {
        HashMap<String,String>hashMap = new HashMap<>();

        //    Request request;
        if (!TextUtils.isEmpty(Access_token) && !TextUtils.isEmpty(Token_type)) {
            hashMap.put("Access_token",Access_token);
            hashMap.put("Token_type",Token_type);
        }

        return hashMap;
    }

    private String getComplateUrl(String url) {
        return BaseUrl + url;
    }

    public void get(String url, final MyBaseCallBack callBack){

        OkHttpUtils.get().url(getComplateUrl(url)).headers(header()).build().execute(new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.onError(call,e,id);
            }

            @Override
            public void onResponse(Object response, int id) {
                callBack.onResponse(response,id);
            }
        });
    }

    public void post(String url,HashMap hashMap, final MyBaseCallBack callBack){

        OkHttpUtils.post().url(getComplateUrl(url)).headers(header()).params(hashMap).build().execute(new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.onError(call,e,id);
            }

            @Override
            public void onResponse(Object response, int id) {
                callBack.onResponse(response,id);
            }
        });
    }




}
