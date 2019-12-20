package com.example.buquduo.Home;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buquduo.Base.ResReturnItem;
import com.example.buquduo.Lib.GlideImageLoader;
import com.example.buquduo.Network.BQDHttpTool;
import com.example.buquduo.Network.MyBaseCallBack;
import com.example.buquduo.Network.TheCallBack;
import com.example.buquduo.Network.WBHttpUtils;
import com.example.buquduo.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Response;


public class HomeActivity extends Fragment implements OnBannerListener {

    View myview;
    Button stepButton;
    TextView steptxt;
    TextView stepinfoTxt;
    Banner banner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        myview = inflater.inflate(R.layout.activity_home,container,false);

        initView();

        setBannerView();

//        initData();
//
//        updatedata();

        return myview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void initView() {
        Log.d("","initView方法----");


        myview.findViewById(R.id.button_getrun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick(v);
            }
        });

        myview.findViewById(R.id.topbottom_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick(v);
            }
        });
        myview.findViewById(R.id.topbottom_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick(v);
            }
        });

    }

    public void setBannerView() {
//        list_path.add("https://www.blocknew.net/img/portfolio/thumbnails/5.jpg");
//        list_path.add("https://www.blocknew.net/img/portfolio/thumbnails/6.jpg");
//        list_path.add("https://www.blocknew.net/img/portfolio/thumbnails/4.jpg");

        banner = (Banner) myview.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.isAutoPlay(true);
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
//        banner.setImages(list_path).setOnBannerListener(this).start();


        String url = "api/Banners";
        BQDHttpTool.getShareInstance().get(url, new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
//                Log.d("banner失败",e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {

                Gson gson = new Gson();
                ArrayList<BannerItem> list = (ArrayList<BannerItem>)((ResReturnItem)response).data;
                List<BannerItem> newList = gson.fromJson(gson.toJson(list),new TypeToken<List<BannerItem>>(){}.getType());

                //整合数据
                ArrayList<String> list_path =  new ArrayList<>();
                for (BannerItem obj:newList){
                    list_path.add(obj.image_url);
                }
//                Log.e("得到的banner新数组",list_path.toString());
                setupbanners(list_path);
            }
        });
    }

    public void setupbanners(final List<String> images) {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<String> newimg = images;
                banner.setImages(newimg).setOnBannerListener(HomeActivity.this).start();
            }
        });
    }


    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.button_getrun:
                Toast.makeText(this.getContext(),"领取",Toast.LENGTH_SHORT).show();
                break;
            case R.id.topbottom_btn1:
                Toast.makeText(this.getContext(),"点击邀请好友",Toast.LENGTH_SHORT).show();
                break;

            case R.id.topbottom_btn2:
                Toast.makeText(this.getContext(),"点击红包",Toast.LENGTH_SHORT).show();
                break;
                default:break;
        }
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this.getContext(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }

    public void initData() {
        Log.d("","initdata方法");
    }

    public void updatedata() {

    }

}