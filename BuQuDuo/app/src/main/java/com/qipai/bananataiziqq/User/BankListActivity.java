package com.qipai.bananataiziqq.User;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qipai.bananataiziqq.Base.MyTool;
import com.qipai.bananataiziqq.Base.ResReturnItem;
import com.qipai.bananataiziqq.Network.BQDHttpTool;
import com.qipai.bananataiziqq.Network.MyBaseCallBack;
import com.qipai.bananataiziqq.R;
import com.qipai.bananataiziqq.bar.OnTitleBarListener;
import com.qipai.bananataiziqq.bar.TitleBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class BankListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<BankItem> list = new ArrayList<BankItem>();
    BankAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);


        initview();

        initData();

        setAdapter();

        setback();
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
//        list.add(new BankItem("jianshe","建设银行","0",0));
//        list.add(new BankItem("gongshang","工商银行","1",0));
//        list.add(new BankItem("nongye","农业银行","2",0));


        BQDHttpTool.getShareInstance().get("api/banks", new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(BankListActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                Gson gson = new Gson();
                String dataStr = gson.toJson(((ResReturnItem)response).data);

                ArrayList<BankItem>list1 = gson.fromJson(dataStr,new TypeToken<BankItem>(){}.getType());
                list.clear();
                list.addAll(list1);

                reloadData();
            }
        });
    }

    private void reloadData() {
        adapter.notifyDataSetChanged();
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
