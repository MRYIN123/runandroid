package com.example.buquduo.Network;

import android.content.Context;
import android.util.Log;

//import com.example.blocknew.dianzanredpacket.MoreFile.ContactActivity;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WBHttpUtils {

    private static final byte[] LOCKER = new byte[0];
    private static WBHttpUtils shareInstance;
    private OkHttpClient okHttpClient;


    private WBHttpUtils(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(20,TimeUnit.SECONDS);//读取超时
        builder.connectTimeout(6,TimeUnit.SECONDS);//链接超时
        builder.writeTimeout(60,TimeUnit.SECONDS);//写入超时
        //支持https请求，跳过证书验证
//        builder.sslSocketFactory(cre)

        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        okHttpClient = builder.build();
    }


    /*
    单例模式获取WBHttpClient
     */
    public static WBHttpUtils getShareInstance(){
        if (shareInstance == null) {
            synchronized (LOCKER) {
                if (shareInstance == null) {
                    shareInstance = new WBHttpUtils();
                }
            }
        }
        return shareInstance;
    }


    /*
    自定义网络回调接口
     */
    public interface WBNetCall{
        void success(Call call, Response response) throws IOException;
        void failed(Call call, IOException e);
    }


    /*
    get请求，同步方式
     */

    /*
    post请求，同步方式
     */

    /*
    get请求，异步方式
     */
    public void getDataAsyn(String url,final WBNetCall wbNetCall){
        //1构造请求
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        //2将Request封装为call
        Call call = okHttpClient.newCall(request);
        //3执行call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                wbNetCall.failed(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                wbNetCall.success(call,response);
            }
        });
    }


    /*
    post请求，异步方式
    url:url
    params:params
    wbNetCall:wbNetCall
     */
    public void postDataAsyn(String url, Map<String,String>params, final WBNetCall wbNetCall){
        //1构造Requestbody
        RequestBody builder = setRequestBody(params);

        //2构造Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(builder).url(url).build();

        //3将Request封装成call
        Call call = okHttpClient.newCall(request);

        //4执行call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                wbNetCall.failed(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                wbNetCall.success(call,response);
            }
        });

    }

    /*
     post的请求参数，构造RequestBody
     */
    private RequestBody setRequestBody(Map<String,String>BodyParams){
        RequestBody body = null;
        FormBody.Builder fromBuilder = new FormBody.Builder();
        if (BodyParams != null){
            Iterator<String>iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()){
                key = iterator.next().toString();
                fromBuilder.add(key,BodyParams.get(key));
                Log.d("post http","param="+key+"==="+BodyParams.get(key));
            }
        }
        body = fromBuilder.build();
        return  body;
    }

    /**
     * 判断网络是否可用
     * @param context
     * @return
     */
//    public static boolean isNetworkAvailable(Context context) {
//        ContactActivity cm = (ContactActivity) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (cm == null) {
//        } else {
//            //如果仅仅是用来判断网络连接
//            //则可以使用cm.getActiveNetworkInfo().isAvailable();
//            Newr[] info = cm.getAllNetworkInfo();
//            if (info != null) {
//                for (int i = 0; i < info.length; i++) {
//                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }

    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     * @return
     */
//    public SSLSocketFactory createSSLSocketFactory() {
//        SSLSocketFactory ssfFactory = null;
//        try {
//            SSLContext sc = SSLContext.getInstance("TLS");
//            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
//            ssfFactory = sc.getSocketFactory();
//        } catch (Exception e) {
//        }
//        return ssfFactory;
//    }

    /**
     * 用于信任所有证书
     */
//    class TrustAllCerts implements X509TrustManager {
//        @Override
//        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//        }
//        @Override
//        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//
//        }
//        @Override
//        public X509Certificate[] getAcceptedIssuers() {
//            return new X509Certificate[0];
//        }
//    }




}
