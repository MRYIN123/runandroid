package com.example.buquduo.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;

import java.util.List;

public class AboutusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
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
