package com.example.buquduo.News;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.buquduo.Base.ResReturnItem;
import com.example.buquduo.Common.WebNewActivity;
import com.example.buquduo.Home.BannerItem;
import com.example.buquduo.Network.AHttpUtils;
import com.example.buquduo.Network.TheCallBack;
import com.example.buquduo.Network.WBHttpUtils;
import com.example.buquduo.R;
import com.example.buquduo.User.UserAdapter;
import com.example.buquduo.User.ViewItems;
import com.example.buquduo.ZhuanZhuan.ZhuanItem;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import com.gxz.PagerSlidingTabStrip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;


public class NewsActivity extends Fragment {

    View myView;
    ListView listView;
    NewsAdapter adapter;
    ArrayList<NewsItem> categoryList = new ArrayList<NewsItem>();;
    ArrayList<NewsItem> datalist = new ArrayList<NewsItem>();;
    private PagerSlidingTabStrip tabs;
//    private ViewPager pager;
    private int offset = 1;
    private String category = "toutiao";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_news,container,false);

//        initData();
        Log.d("","onCreateView--------");

        initView();

//        setTitleBars();

        setAdapter();

        return myView;
    }

    public void setTitleBars() {
        tabs = (PagerSlidingTabStrip) myView.findViewById(R.id.zhuan_tabs);
        ArrayList<String> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add("TAB "+i);
        }
//        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),list));
//        tabs.setViewPager(pager);
//        pager.setCurrentItem(1);

        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);

        // 设置Tab的分割线的颜色
        tabs.setDividerColor(getResources().getColor(R.color.color999999));
        tabs.setDividerPaddingTopBottom(12);
        tabs.setUnderlineHeight(1);
        tabs.setUnderlineColor(getResources().getColor(R.color.color333333));

        // 设置Tab 指示器Indicator的高度,传入的是dp
        tabs.setIndicatorHeight(4);
        tabs.setIndicatorColor(getResources().getColor(R.color.color_base));

        tabs.setTextSize(16);
        tabs.setSelectedTextColor(getResources().getColor(R.color.color_base));
        tabs.setTextColor(getResources().getColor(R.color.color333333));
        tabs.setTabPaddingLeftRight(24);

        //是否支持动画渐变(颜色渐变和文字大小渐变)
        tabs.setFadeEnabled(false);
        // 设置最大缩放,是正常状态的0.3倍
        tabs.setZoomMax(0.3F);

        //这是当点击tab时内容区域Viewpager是否是左右滑动,默认是true
        tabs.setSmoothScrollWhenClickTab(true);

        tabs.setOnPagerTitleItemClickListener(new PagerSlidingTabStrip.OnPagerTitleItemClickListener() {
            @Override
            public void onSingleClickItem(int position) {
                reloadEveryPage(position);
            }

            @Override
            public void onDoubleClickItem(int position) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("","onResume--------");
        getCategory();

        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("","onStart--------");

        initData();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("","onPause--------");

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("","onDestroy--------");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("","onCreate--------");

    }

    public void reloadEveryPage(int position) {
        Toast.makeText(this.getContext(), "单击", Toast.LENGTH_SHORT).show();


    }


    public void initView() {
        listView = myView.findViewById(R.id.newlist);
//        pager = (ViewPager) findViewById(R.id.pager);

    }

    public void getCategory() {
        String url = getResources().getString(R.string.url_base) + "api/news/category";
        WBHttpUtils.getShareInstance().getDataAsyn(url, new WBHttpUtils.WBNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                String resStr = response.body().string();
                Gson gson = new Gson();
                ResReturnItem item = gson.fromJson(resStr,ResReturnItem.class);

                ArrayList<NewsItem>list = (ArrayList<NewsItem>)item.data;
                ArrayList<NewsItem> categorylist1 = gson.fromJson(gson.toJson(list),new TypeToken<ArrayList<NewsItem>>(){}.getType());

                categoryList = categorylist1;
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    public void initData() {
        datalist.clear();
//        datalist.add(new NewsItem("这是我的数据1","这是我的数据详情1","https://blocknew.net",
//                "https://www.blocknew.net/img/portfolio/thumbnails/2.jpg","1","2019-11-29","100"));
//        datalist.add(new NewsItem("这是我的数据2","这是我的数据详情2","http://www.woshipm.com",
//                "https://www.blocknew.net/img/portfolio/thumbnails/2.jpg","1","2019-11-29","100"));
//        datalist.add(new NewsItem("这是我的数据3","这是我的数据详情3","https://www.qq.com",
//                "https://www.blocknew.net/img/portfolio/thumbnails/2.jpg","1","2019-11-29","100"));
//        datalist.add(new NewsItem("这是我的数据4","这是我的数据详情3","https://www.jianshu.com/p/deb1c62bce5f",
//                "https://www.blocknew.net/img/portfolio/thumbnails/2.jpg","1","2019-11-29","100"));

        String url = getResources().getString(R.string.url_base) + "api/news" + "?page=" + offset + "&category=" + category;

        WBHttpUtils.getShareInstance().getDataAsyn(url, new WBHttpUtils.WBNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                String resStr = response.body().string();
//                Log.d("返回的newstring----=",resStr);

                Gson gson = new Gson();
                ResReturnItem item = gson.fromJson(resStr,ResReturnItem.class);
                Log.d("返回的newsdebug=","解析啦");

                ArrayList<NewsItem> list = (ArrayList<NewsItem>)item.data;
                Log.d("返回的news=",list.toString());


                ArrayList<NewsItem> newList = gson.fromJson(gson.toJson(list),new TypeToken<ArrayList<NewsItem>>(){}.getType());
                datalist.addAll(newList);
                reloadData();
            }

            @Override
            public void failed(Call call, IOException e) {
                Log.d("news打印","new错误");
                makeToast(e.getMessage());
            }
        });

    }

    public void reloadData() {
        Log.d("list==","刷新数据==");
//        Log.d("",datalist.toString());

        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void makeToast(final String toastStr){

        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(),toastStr.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void setAdapter() {
        adapter = new NewsAdapter(this.getActivity(),R.layout.newsitem,datalist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { gotonext(position);
            }
        });
    }

    public void gotonext(int position) {
        NewsItem item = datalist.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uri",item.getUrl());


        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(this.getActivity(), WebNewActivity.class);
        this.startActivity(intent);
    }


}
