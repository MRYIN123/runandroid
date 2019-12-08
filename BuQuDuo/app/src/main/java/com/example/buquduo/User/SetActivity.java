package com.example.buquduo.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;

import java.util.ArrayList;

public class SetActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<ViewItems>list;
    SetAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        initview();

        initData();

        setAdapter();
    }


    TitleBar titleBar;
    public  void  setback() {
        titleBar = findViewById(R.id.titlebar_set);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                SetActivity.this.finish();
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
        listView = findViewById(R.id.setlistview);


    }

    public void initData() {
        list = new ArrayList<ViewItems>();

        list.add(new ViewItems("关于我们"));
        list.add(new ViewItems("清除缓存"));
        list.add(new ViewItems("检查更新"));
        list.add(new ViewItems("退出登录"));


    }

    public void setAdapter() {
        adapter = new SetAdapter(this,R.layout.set_item,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gotonext(i);
            }
        });
    }

    private void gotonext(int v){
        ViewItems items = list.get(v - 1);

        if (items.getLeftTitle() == "关于我们") {
            Intent intent = new Intent();
            intent.setClass(this,AboutusActivity.class);
            startActivity(intent);

        }else if (items.getLeftTitle() == "清除缓存") {
            Toast.makeText(this,"清除成功",Toast.LENGTH_SHORT).show();

        }else if (items.getLeftTitle() == "检查更新") {
            Toast.makeText(this,"最新版本",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this,"退出登录",Toast.LENGTH_SHORT).show();

        }
    }



}
