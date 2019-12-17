package com.example.buquduo.Network;

import android.os.Handler;
import android.util.Log;

import com.vector.update_app.HttpManager;

import java.io.IOException;
import java.util.Map;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class AHttpUtils  {

    private static volatile AHttpUtils instance;
    private static final String TAG = "AHttpUtils";
    public static Handler ler = new Handler();
    private AHttpUtils() {}





    /**
     * 双重检测锁-单例模式
     * @return
     */
    public static AHttpUtils getInstance() {
        if(instance == null) {
            synchronized (AHttpUtils.class) {
                if (instance == null) {
                    instance = new AHttpUtils();
                }
            }
        }
        return instance;
    }


    /**
     * @网络请求--get请求
     */
    public void get(String url, Map<String,String> map, final TheCallBack callback, final Class cls) {
        //对url和参数做一下拼接处理
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        if(url.contains("?")) {
            //如果？不在最后一位
            if(sb.indexOf("?") != sb.length()-1) {
                sb.append("&");
            }
        }else {
            sb.append("?");
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        if(sb.indexOf("&") != -1) {
            sb.deleteCharAt(sb.lastIndexOf("&"));
        }
        Log.i(TAG, "get url: " + sb);
        //new 一个OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(sb.toString())
                .build();

        Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Log.e(TAG,"onFailure:"+ e.getCause().getStackTrace() + e.getMessage());
                ler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailed("fail",e);
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                final Object o = ABaseGson.getInstance().fromJson(result, cls);
                ler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess("success",o);
                    }
                });
            }
        });
    }

    /**
     * @网络请求--post请求
     */
    public void post(String url, Map<String,String> map, final TheCallBack callback, final Class cls) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        for(Map.Entry<String,String> entry:map.entrySet()) {
            builder.add(entry.getKey(),entry.getValue());
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Log.e(TAG,"onFailure:"+e.getCause().getStackTrace() + e.getMessage());
                ler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailed("fail",e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                final Object o = ABaseGson.getInstance().fromJson(result, cls);
                ler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess("success",o);
                    }
                });
            }
        });
    }


}
