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

public class GoldRecordActivity extends AppCompatActivity {


    TitleBar titleBar;
    ListView listView;
    ArrayList<GoldItem> list;
    GoldRecordAdapter recordAdapter;
    View headView;
    GoldItem goldItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_record);

        configTitleBar();

        initview();

        initdata();

        configAdapter();

        updateHeadUI();
    }


    public void configTitleBar() {
        titleBar = findViewById(R.id.titlebar_gorldrecord);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                GoldRecordActivity.this.finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                gotowithdrawList();
            }
        });
    }


    public void initview() {
        headView = getLayoutInflater().inflate(R.layout.goldrecord_head,null);
        listView = findViewById(R.id.list_getrecord);
        listView.addHeaderView(headView);

    }

    public void initdata() {
        list = new ArrayList<GoldItem>();
        list.add(new GoldItem("100","签到奖励积分","2019-12-06 08:28:34"));

        list.add(new GoldItem("70","签到奖励积分","2019-11-29 08:28:32"));
        list.add(new GoldItem("100","看新闻给积分","2019-11-29 18:28:30"));
        list.add(new GoldItem("80","连续签到积分","2019-11-28 08:28:39"));
        list.add(new GoldItem("100","签到奖励积分","2019-11-21 08:28:30"));
        list.add(new GoldItem("60","看视频积分","2019-11-21 09:23:31"));
        list.add(new GoldItem("120","运动积分","2019-10-02 08:28:30"));

        goldItem = new GoldItem("100","200",0);
    }


    public void configAdapter() {
        recordAdapter = new GoldRecordAdapter(this,R.layout.goldrecord_item,list);
        listView.setAdapter(recordAdapter);
    }

    public void updateHeadUI() {
        TextView allcoin = headView.findViewById(R.id.recordhead_allcoin);
        TextView todaycoin = headView.findViewById(R.id.recordhead_todaycoin);
        TextView lastcoin = headView.findViewById(R.id.recordhead_lastcoin);
        Button withdrawbtn = headView.findViewById(R.id.recordhead_withdrwa);

        withdrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotowithdraw();
            }
        });

    }

    public void gotowithdraw() {
        Intent intent = new Intent();
        intent.setClass(this,WithDrawActivity.class);
        startActivity(intent);
    }

    public void gotowithdrawList() {
        Intent intent = new Intent();
        intent.setClass(this,WithDrawListActivity.class);
        startActivity(intent);
    }










}
