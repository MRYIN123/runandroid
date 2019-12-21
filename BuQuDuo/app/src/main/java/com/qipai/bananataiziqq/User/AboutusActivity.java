package com.qipai.bananataiziqq.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.qipai.bananataiziqq.R;
import com.qipai.bananataiziqq.bar.OnTitleBarListener;
import com.qipai.bananataiziqq.bar.TitleBar;

public class AboutusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        setTitleBarac();
    }

    TitleBar titleBar;
    private void setTitleBarac () {
        titleBar = findViewById(R.id.titlebar_aboutus);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                AboutusActivity.this.finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }




}
