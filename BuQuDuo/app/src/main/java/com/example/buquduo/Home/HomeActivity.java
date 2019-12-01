package com.example.buquduo.Home;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.buquduo.Lib.GlideImageLoader;
import com.example.buquduo.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class HomeActivity extends Fragment implements OnBannerListener {

    View myview;
    private ArrayList<String> list_path =  new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        myview = inflater.inflate(R.layout.activity_home,container,false);

        initView();

        setBannerView();

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
        list_path.clear();
        list_path.add("https://www.blocknew.net/img/portfolio/thumbnails/5.jpg");
        list_path.add("https://www.blocknew.net/img/portfolio/thumbnails/6.jpg");
        list_path.add("https://www.blocknew.net/img/portfolio/thumbnails/4.jpg");


        Banner banner = (Banner) myview.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.isAutoPlay(true);
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(list_path).setOnBannerListener(this).start();

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

}