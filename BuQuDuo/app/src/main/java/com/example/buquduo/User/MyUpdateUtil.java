package com.example.buquduo.User;

import com.example.buquduo.Network.WBHttpUtils;
import com.vector.update_app.HttpManager;

import java.io.IOException;
import java.util.Map;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Response;

public class MyUpdateUtil implements HttpManager {


    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull Callback callBack) {
        WBHttpUtils.getShareInstance().getDataAsyn(url, new WBHttpUtils.WBNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {

            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
        WBHttpUtils.getShareInstance().postDataAsyn(url, params, new WBHttpUtils.WBNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {

            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull FileCallback callback) {
//        WBHttpUtils.getShareInstance().downlaodDataAsyn(this,url,path,fileName,new WBHttpUtils.WBNetCall());




    }


}
