package com.example.buquduo.News;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.buquduo.Common.WebNewActivity;
import com.example.buquduo.R;
import com.example.buquduo.User.UserAdapter;
import com.example.buquduo.User.ViewItems;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class NewsActivity extends Fragment {

    View myView;
    ListView listView;
    NewsAdapter adapter;
    ArrayList<NewsItem> datalist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_news,container,false);

        initData();

        initView();

        setAdapter();

        return myView;
    }

    public void initView() {
        listView = myView.findViewById(R.id.newlist);

    }

    public void initData() {
        datalist = new ArrayList<NewsItem>();
        datalist.add(new NewsItem("这是我的数据1","这是我的数据详情1","https://blocknew.net",
                "https://www.blocknew.net/img/portfolio/thumbnails/2.jpg","1","2019-11-29","100"));
        datalist.add(new NewsItem("这是我的数据2","这是我的数据详情2","http://www.woshipm.com",
                "https://www.blocknew.net/img/portfolio/thumbnails/2.jpg","1","2019-11-29","100"));
        datalist.add(new NewsItem("这是我的数据3","这是我的数据详情3","https://www.qq.com",
                "https://www.blocknew.net/img/portfolio/thumbnails/2.jpg","1","2019-11-29","100"));
        datalist.add(new NewsItem("这是我的数据4","这是我的数据详情3","https://www.jianshu.com/p/deb1c62bce5f",
                "https://www.blocknew.net/img/portfolio/thumbnails/2.jpg","1","2019-11-29","100"));
    }

    public void setAdapter() {
        adapter = new NewsAdapter(this.getActivity(),R.layout.newsitem,datalist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotonext(position);
            }
        });

    }

    public void gotonext(int position) {
        NewsItem item = datalist.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uri",item.getTarget());

        Toast.makeText(this.getContext(),item.getTarget(),Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(this.getActivity(), WebNewActivity.class);
        this.startActivity(intent);
    }


}
