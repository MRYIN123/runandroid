package com.example.buquduo.Network;

public interface TheCallBack {
    void onSuccess(String tag, Object o);

    void onFailed(String tag, Exception e);
}
