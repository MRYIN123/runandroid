package com.qipai.bananataiziqq.User;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.qipai.bananataiziqq.R;
import com.qipai.bananataiziqq.bar.OnTitleBarListener;
import com.qipai.bananataiziqq.bar.TitleBar;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class RunHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initView();

        setback();
    }

    TitleBar titleBar;
    public  void  setback() {
        titleBar = findViewById(R.id.historytitlebar);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                RunHistoryActivity.this.finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }


    ListView listView;
    ArrayList<ViewItems> datalist;
    UserAdapter adapter;
    public void initView() {
        listView = findViewById(R.id.historylist);

    }


}