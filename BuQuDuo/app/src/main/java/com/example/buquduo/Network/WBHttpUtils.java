package com.example.buquduo.Network;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

//import com.example.blocknew.dianzanredpacket.MoreFile.ContactActivity;

import com.vector.update_app.HttpManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;

public class WBHttpUtils  {

    private static final byte[] LOCKER = new byte[0];
    private static WBHttpUtils shareInstance;
    private OkHttpClient okHttpClient;
    private String Access_token = "";
    private String Token_type = "";


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


    public void setToken_type(String token_type) {
        Token_type = token_type;
    }

    public void setAccess_token(String access_token) {
        Access_token = access_token;
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

        Request request;
        if (!TextUtils.isEmpty(Access_token) && !TextUtils.isEmpty(Token_type)) {
            request = builder.get().addHeader("Access_token",Access_token).addHeader("Token_type",Token_type).url(url).build();

        }else {
            request = builder.get().url(url).build();

        }


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
        Request request;

        if (!TextUtils.isEmpty(Access_token) && !TextUtils.isEmpty(Token_type)) {
            request = requestBuilder.post(builder).addHeader("Access_token",Access_token).addHeader("Token_type",Token_type).url(url).build();

        }else {
            request = requestBuilder.post(builder).url(url).build();

        }


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


    /*
   downlaodDataAsyn
   url:url
   params:params
   wbNetCall:wbNetCall
   Context context, String url, String filedir, String filename, final DownloadResponseHandler downloadResponseHandler) {
    */
    public void downlaodDataAsyn(Context context,String url, String filedir, String filename, final DownloadManager downloadResponseHandler){
        Request request;
        if(context == null) {
            request = new Request.Builder()
                    .url(url)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .tag(context)
                    .build();
        }

//        okHttpClient.newBuilder()
//                .addNetworkInterceptor(new Interceptor() {      //设置拦截器
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Response originalResponse = chain.proceed(chain.request());
//                        return originalResponse.newBuilder()
//                                .body(new RealResponseBody(originalResponse.body(), downloadResponseHandler))
//                                .build();
//                    }
//                })
//                .build()
//                .newCall(request)
//                .enqueue(new MyDownloadCallback(new Handler(), downloadResponseHandler, filedir, filename));

    }

    //下载回调
//    private class MyDownloadCallback implements Callback {
//
//        private Handler mHandler;
//        private DownloadResponseHandler mDownloadResponseHandler;
//        private String mFileDir;
//        private String mFilename;
//
//        public MyDownloadCallback(Handler handler, DownloadResponseHandler downloadResponseHandler,
//                                  String filedir, String filename) {
//            mHandler = handler;
//            mDownloadResponseHandler = downloadResponseHandler;
//            mFileDir = filedir;
//            mFilename = filename;
//        }
//
//        @Override
//        public void onFailure(Call call, final IOException e) {
////            LogUtils.e("onFailure", e);
//
//            mHandler.p(new Runnable() {
//                @Override
//                public void run() {
//                    mDownloadResponseHandler.onFailure(e.toString());
//                }
//            });
//        }
//
//        @Override
//        public void onResponse(Call call, final Response response) throws IOException {
//            if(response.isSuccessful()) {
//                File file = null;
//                try {
//                    file = saveFile(response, mFileDir, mFilename);
//                } catch (final IOException e) {
////                    LogUtils.e("onResponse saveFile fail", e);
//
//                    mHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            mDownloadResponseHandler.onFailure("onResponse saveFile fail." + e.toString());
//                        }
//                    });
//                }
//
//                final File newFile = file;
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mDownloadResponseHandler.onFinish(newFile);
//                    }
//                });
//
//            } else {
////                LogUtils.e("onResponse fail status=" + response.code());
//
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mDownloadResponseHandler.onFailure("fail status=" + response.code());
//                    }
//                });
//            }
//        }
//    }


    //保存文件
    private File saveFile(Response response, String filedir, String filename) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            File dir = new File(filedir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, filename);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            return file;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
        }
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
