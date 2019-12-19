package com.example.buquduo.User;

import android.nfc.Tag;
import android.os.Environment;
import android.util.Log;

import com.example.buquduo.Network.WBHttpUtils;
import com.vector.update_app.HttpManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MyUpdateUtil implements HttpManager {


    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
        OkHttpUtils.get().url(url).build().execute(new com.zhy.http.okhttp.callback.Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return null;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                callBack.onResponse(response.toString());
            }
        });
    }

    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
        OkHttpUtils.post().url(url).build().execute(new com.zhy.http.okhttp.callback.Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return null;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                callBack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                callBack.onResponse(response.toString());
            }
        });
    }

    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull final FileCallback callback) {
        String pathfile = Environment.getExternalStorageDirectory().getAbsolutePath();
        //gson-2.2.1.ja
        String destFileName = "buquduodownloadfile";

        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(pathfile,destFileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        callback.onResponse(response);
                    }
                });

    }


}
