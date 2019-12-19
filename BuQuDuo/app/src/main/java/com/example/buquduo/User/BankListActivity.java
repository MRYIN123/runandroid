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

public class BankListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<BankItem> list;
    BankAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);



        initview();

        initData();

        setAdapter();
    }


    TitleBar titleBar;
    public  void  setback() {
        titleBar = findViewById(R.id.titlebar_banklist);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                BankListActivity.this.finish();
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
        listView = findViewById(R.id.banklistview);


    }

    public void initData() {
        list = new ArrayList<BankItem>();

        list.add(new BankItem("jianshe","建设银行","0",0));
        list.add(new BankItem("gongshang","工商银行","1",0));
        list.add(new BankItem("nongye","农业银行","2",0));


    }

    public void setAdapter() {
        adapter = new BankAdapter(this,R.layout.bank_item,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gotonext(i);
            }
        });
    }

    private void gotonext(int v){
        BankItem items = list.get(v - 1);

        BankListActivity.this.finish();
    }



}
