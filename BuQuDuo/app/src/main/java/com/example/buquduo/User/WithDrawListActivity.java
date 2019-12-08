package com.example.buquduo.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;

import java.util.ArrayList;

public class WithDrawListActivity extends AppCompatActivity {

    TitleBar titleBar;
    ListView listView;
    ArrayList<GoldItem> list;
    GoldRecordAdapter recordAdapter;
    GoldItem goldItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw_list);

        configTitleBar();

        initview();

        initdata();

        configAdapter();

    }


    public void configTitleBar() {
        titleBar = findViewById(R.id.titlebar_withdrawlist);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                WithDrawListActivity.this.finish();
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
        listView = findViewById(R.id.list_withdrawlist);


    }

    public void initdata() {
        list = new ArrayList<GoldItem>();
        list.add(new GoldItem("100","积分提现","2019-12-06 08:28:34"));
        list.add(new GoldItem("70","积分提现","2019-11-29 08:28:32"));
        list.add(new GoldItem("100","积分提现","2019-11-29 18:28:30"));
        list.add(new GoldItem("80","积分提现","2019-11-28 08:28:39"));
        list.add(new GoldItem("100","积分提现","2019-11-21 08:28:30"));
        list.add(new GoldItem("60","积分提现","2019-11-21 09:23:31"));
        list.add(new GoldItem("120","积分提现","2019-10-02 08:28:30"));

    }


    public void configAdapter() {
        recordAdapter = new GoldRecordAdapter(this,R.layout.goldrecord_item,list);
        listView.setAdapter(recordAdapter);

    }




}
