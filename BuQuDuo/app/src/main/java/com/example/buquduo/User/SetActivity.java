package com.example.buquduo.User;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.buquduo.Base.MyTool;
import com.example.buquduo.Base.ResReturnItem;
import com.example.buquduo.Network.MyBaseCallBack;
import com.example.buquduo.Network.WBHttpUtils;
import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;
import com.google.gson.Gson;
import com.vector.update_app.UpdateAppManager;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;

public class SetActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<ViewItems>list;
    SetAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        initData();

        initview();

        setAdapter();

        setback();
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
        Toast.makeText(this,"设置啦    ",Toast.LENGTH_SHORT).show();

    }

    public void initData() {
        list = new ArrayList<ViewItems>();

        list.add(new ViewItems("设置支付密码"));
        list.add(new ViewItems("绑定银行卡"));
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
        ViewItems items = list.get(v);
        Log.d("点击的是===",items.getLeftTitle());

        if (items.getLeftTitle() == "关于我们") {
            Intent intent = new Intent();
            intent.setClass(this,AboutusActivity.class);
            startActivity(intent);

        }else if (items.getLeftTitle() == "清除缓存") {
            Toast.makeText(this,"清除成功",Toast.LENGTH_SHORT).show();

        }else if (items.getLeftTitle() == "检查更新") {
            checkVersion();

        }else if (items.getLeftTitle() == "绑定银行卡") {
            bindcard();

        }else if (items.getLeftTitle() == "设置支付密码") {
            setuppwd();

        }else {
            Toast.makeText(this,"退出登录",Toast.LENGTH_SHORT).show();

        }
    }

    private void bindcard() {
        Intent intent = new Intent();
        intent.setClass(this,BindBankActivity.class);
        startActivity(intent);
    }

    private void setuppwd() {
        Intent intent = new Intent();
        intent.setClass(this,SetPayPwdActivity.class);
        startActivity(intent);
    }

    private void checkVersion() {

        Toast.makeText(this,"最新版本",Toast.LENGTH_SHORT).show();

        String url = getResources().getString(R.string.url_base) + "api/version/Android";
        OkHttpUtils.get().url(url).build().execute(new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(SetActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                Gson gson = new Gson();
                VersionItem versionItem = gson.fromJson(gson.toJson(((ResReturnItem)response).data),VersionItem.class);

                //检查是否需要更新
                setupVersionData(versionItem);
            }
        });

    }


    //检查是否需要更新
    public void setupVersionData(VersionItem versionItem) {
        Log.d("version",versionItem.getUpdateDescription());

        String mUpdateUrl = "Android包下载地址url";

        if (MyTool.isNeedUpdate(this,versionItem.getNewVersion())){

            new UpdateAppManager
                    .Builder()
                    //当前Activity
                    .setActivity(this)
                    //更新地址
                    .setUpdateUrl(mUpdateUrl)
                    //实现httpManager接口的对象
                    .setHttpManager(new MyUpdateUtil())
                    .build()
                    .update();

        }else {
            Toast.makeText(this,"当前是最新版本",Toast.LENGTH_SHORT).show();
        }

    }


}
