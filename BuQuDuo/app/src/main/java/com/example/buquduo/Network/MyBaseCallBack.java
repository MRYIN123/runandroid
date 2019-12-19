package com.example.buquduo.Network;

import android.telecom.Call;

import com.example.buquduo.Base.ResReturnItem;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

public abstract class MyBaseCallBack extends Callback {

    @Override
    public ResReturnItem parseNetworkResponse(Response response, int id) throws Exception {

        String string = response.body().string();
        ResReturnItem user = new Gson().fromJson(string, ResReturnItem.class);



        return user;
    }
}
