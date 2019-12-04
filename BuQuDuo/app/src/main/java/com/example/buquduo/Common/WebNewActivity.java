package com.example.buquduo.Common;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.buquduo.R;
import com.example.buquduo.User.HealthActivity;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;
import androidx.appcompat.app.AppCompatActivity;

public class WebNewActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        initview();

        settitlebar();
    }

    TitleBar titleBar;
    public void settitlebar() {
        titleBar = findViewById(R.id.webtitlebar);

        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                WebNewActivity.this.finish();
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
        Bundle bundle = this.getIntent().getExtras();
        final String uri = bundle.getString("uri");

        webView = findViewById(R.id.newsweb);
        webView.loadUrl(uri);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                //
                view.loadUrl(uri);
                return true;
            }
        });
    }


}