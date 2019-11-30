package com.example.buquduo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.buquduo.Common.CustomViewPager;
import com.example.buquduo.Common.ViewPagerFragmentAdapter;
import com.example.buquduo.Home.HomeActivity;
import com.example.buquduo.News.NewsActivity;
import com.example.buquduo.User.UserActivity;
import com.example.buquduo.ZhuanZhuan.ZhuanActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    String[] titleName = new String[] {"运动","看看","赚赚","我的"};
    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    CustomViewPager mViewPager;
    ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    FragmentManager mFragmentManager;


    public LinearLayout firstlayout;
    public LinearLayout secondlayou;
    public LinearLayout thirdlayout;
    public LinearLayout fourlayout;

    //bottom
    private TextView firstTextView;
    private TextView secondTextView;
    private TextView thirdTextView;
    private TextView fourTxtView;
    private ImageView firstImg;
    private ImageView secondImg;
    private ImageView thirdImg;
    private ImageView fourImg;

    private int selectIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);

        initFragmentList();
        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager, mFragmentList);

        initView();
        initViewPager();

    }

    public void initFragmentList() {
        mFragmentList.add(new HomeActivity());
        mFragmentList.add(new NewsActivity());
        mFragmentList.add(new ZhuanActivity());
        mFragmentList.add(new UserActivity());
        Log.d("","进入initFragmentList");
    }

    public void initView() {
        mViewPager = (CustomViewPager) findViewById(R.id.ViewPagerLayout);

        firstTextView = findViewById(R.id.firstTextView);
        secondTextView = findViewById(R.id.secondTextView);
        thirdTextView = findViewById(R.id.thirdTextView);
        fourTxtView = findViewById(R.id.fourTextView);

        firstImg = findViewById(R.id.firstImageView);
        secondImg = findViewById(R.id.secondImageView);
        thirdImg = findViewById(R.id.thirdImageView);
        fourImg = findViewById(R.id.fourImageView);


        firstlayout = (LinearLayout) findViewById(R.id.firstLinearLayout);
        firstlayout.setOnClickListener(this);

        secondlayou = (LinearLayout) findViewById(R.id.secondLinearLayout);
        secondlayou.setOnClickListener(this);

        thirdlayout = (LinearLayout) findViewById(R.id.thirdLinearLayout);
        thirdlayout.setOnClickListener(this);

        fourlayout = (LinearLayout) findViewById(R.id.fourLinearLayout);
        fourlayout.setOnClickListener(this);


        Log.d("","-----initView");
    }

    public void initViewPager() {
        mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setScanScroll(false);
        mViewPager.setCurrentItem(0);
        updateBottomTxtUI();
        Log.d("","----initViewPager");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.firstLinearLayout:
                mViewPager.setCurrentItem(0);
                selectIndex = 0;
                break;
            case R.id.secondLinearLayout:
                mViewPager.setCurrentItem(1);
                selectIndex = 1;
                break;
            case R.id.thirdLinearLayout:
                mViewPager.setCurrentItem(2);
                selectIndex = 2;
                break;
            case R.id.fourLinearLayout:
                mViewPager.setCurrentItem(3);
                selectIndex = 3;
                break;
            default:
                break;
        }
        updateBottomTxtUI();
    }


    public void updateBottomTxtUI(){
        firstlayout.setSelected(false);
        secondlayou.setSelected(false);
        thirdlayout.setSelected(false);
        fourlayout.setSelected(false);

        if (selectIndex == 0) {
            firstlayout.setSelected(true);

            firstTextView.setTextColor(this.getResources().getColor(R.color.color_base1));
            firstImg.setImageResource(R.drawable.paobu);

            secondTextView.setTextColor(Color.GRAY);
            secondImg.setImageResource(R.drawable.yueduhui);

            thirdTextView.setTextColor(Color.GRAY);
            thirdImg.setImageResource(R.drawable.zhuanbahui);

            fourTxtView.setTextColor(Color.GRAY);
            fourImg.setImageResource(R.drawable.wodehui1);

        }else if (selectIndex == 1){
            secondlayou.setSelected(true);
            firstTextView.setTextColor(Color.GRAY);
            firstImg.setImageResource(R.drawable.paobuhui);

            secondTextView.setTextColor(this.getResources().getColor(R.color.color_base1));
            secondImg.setImageResource(R.drawable.yuedu);

            thirdTextView.setTextColor(Color.GRAY);
            thirdImg.setImageResource(R.drawable.zhuanbahui);

            fourTxtView.setTextColor(Color.GRAY);
            fourImg.setImageResource(R.drawable.wodehui1);

        }else if (selectIndex == 2){
            thirdlayout.setSelected(true);
            firstTextView.setTextColor(Color.GRAY);
            firstImg.setImageResource(R.drawable.paobuhui);

            secondTextView.setTextColor(Color.GRAY);
            secondImg.setImageResource(R.drawable.yueduhui);

            thirdTextView.setTextColor(this.getResources().getColor(R.color.color_base1));
            thirdImg.setImageResource(R.drawable.zhuanba);

            fourTxtView.setTextColor(Color.GRAY);
            fourImg.setImageResource(R.drawable.wodehui1);

        }else {
            fourlayout.setSelected(true);
            firstTextView.setTextColor(Color.GRAY);
            firstImg.setImageResource(R.drawable.paobuhui);

            secondTextView.setTextColor(Color.GRAY);
            secondImg.setImageResource(R.drawable.yueduhui);

            thirdTextView.setTextColor(Color.GRAY);
            thirdImg.setImageResource(R.drawable.zhuanbahui);

            fourTxtView.setTextColor(this.getResources().getColor(R.color.color_base1));
            fourImg.setImageResource(R.drawable.wode);

        }
    }

    class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d("","onPageScrooled----------------"+position);
        }

        @Override
        public void onPageSelected(int position) {
            Log.d("","onPageSelected----------------"+position);


            boolean[] state = new boolean[titleName.length];
            state[position] = true;
//            updateBottomLinearLayoutSelect(state[0], state[1]);

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d("","onPageScrollStateChanged");
        }
    }
}
