package com.qipai.bananataiziqq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qipai.bananataiziqq.Base.MyTool;
import com.qipai.bananataiziqq.Common.CustomViewPager;
import com.qipai.bananataiziqq.Common.ViewPagerFragmentAdapter;
import com.qipai.bananataiziqq.Home.HomeActivity;
import com.qipai.bananataiziqq.Login.Customer;
import com.qipai.bananataiziqq.News.NewsActivity;
import com.qipai.bananataiziqq.User.UserActivity;
import com.qipai.bananataiziqq.ZhuanZhuan.ZhuanActivity;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

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
    private String APP_ID = "";
    IWXAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
////            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
////            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//
////            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
////            window.setStatusBarColor(Color.BLUE);
////            window.setNavigationBarColor(Color.TRANSPARENT);
//
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
//
//        }


        mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);

        initFragmentList();
        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager, mFragmentList);

        initView();
        initViewPager();

        regToWx();

    }

    private void regToWx() {
        api = WXAPIFactory.createWXAPI(this, APP_ID, false);
        api.registerApp(APP_ID);

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        String responseInfo = sp.getString("responseInfo", "");

        if (!responseInfo.isEmpty()) {
            try {
                Gson gson = new Gson();
                Customer customer = gson.fromJson(responseInfo,Customer.class);
                //得到头像和名字


            } catch (JsonIOException e) {
                e.printStackTrace();
            }
//            tv.setText("昵称：" + nickname + "\n" + "头像：" + headimgurl);
            SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
            editor.clear();
            editor.commit();
        }
    }


    //微信登录
    public void gotoWx() {
        if (!api.isWXAppInstalled()) {
            MyTool.makeToast(MainActivity.this,"您的设备未安装微信客户端");

        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        }
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
            firstImg.setImageResource(R.mipmap.zou1);

            secondTextView.setTextColor(Color.GRAY);
            secondImg.setImageResource(R.mipmap.kankan);

            thirdTextView.setTextColor(Color.GRAY);
            thirdImg.setImageResource(R.mipmap.zhuanzhuan);

            fourTxtView.setTextColor(Color.GRAY);
            fourImg.setImageResource(R.mipmap.wode);

        }else if (selectIndex == 1){
            secondlayou.setSelected(true);
            firstTextView.setTextColor(Color.GRAY);
            firstImg.setImageResource(R.mipmap.zouzou);

            secondTextView.setTextColor(this.getResources().getColor(R.color.color_base1));
            secondImg.setImageResource(R.mipmap.kan);

            thirdTextView.setTextColor(Color.GRAY);
            thirdImg.setImageResource(R.mipmap.zhuanzhuan);

            fourTxtView.setTextColor(Color.GRAY);
            fourImg.setImageResource(R.mipmap.wode);

        }else if (selectIndex == 2){
            thirdlayout.setSelected(true);
            firstTextView.setTextColor(Color.GRAY);
            firstImg.setImageResource(R.mipmap.zouzou);

            secondTextView.setTextColor(Color.GRAY);
            secondImg.setImageResource(R.mipmap.kankan);

            thirdTextView.setTextColor(this.getResources().getColor(R.color.color_base1));
            thirdImg.setImageResource(R.mipmap.zhuan1);

            fourTxtView.setTextColor(Color.GRAY);
            fourImg.setImageResource(R.mipmap.wode);

        }else {
            fourlayout.setSelected(true);
            firstTextView.setTextColor(Color.GRAY);
            firstImg.setImageResource(R.mipmap.zouzou);

            secondTextView.setTextColor(Color.GRAY);
            secondImg.setImageResource(R.mipmap.kankan);

            thirdTextView.setTextColor(Color.GRAY);
            thirdImg.setImageResource(R.mipmap.zhuanzhuan);

            fourTxtView.setTextColor(this.getResources().getColor(R.color.color_base1));
            fourImg.setImageResource(R.mipmap.wo);

        }
    }

    class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.d("","onPageScrooled----------------"+position);
        }

        @Override
        public void onPageSelected(int position) {
//            Log.d("","onPageSelected----------------"+position);


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
