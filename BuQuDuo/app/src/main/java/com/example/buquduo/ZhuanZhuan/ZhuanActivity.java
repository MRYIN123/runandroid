package com.example.buquduo.ZhuanZhuan;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buquduo.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class ZhuanActivity extends Fragment {

    private TextView mygoldtxt;
    private TextView signInfoTxt;
    private TextView signwarmTxt;
    private Button signBtn;

    View myview;
    ListView listView;
    View headView;
    ArrayList<ZhuanItem>list;
    ZhuanAdapter adapter;

    SignItem signItem;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.activity_zhuan,container,false);

        initData();

        initView();

        setadapter();

        updateHeadUI();

        return myview;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    public void initView() {
        headView = getLayoutInflater().inflate(R.layout.zhuanhead,null);
        listView = myview.findViewById(R.id.zhuan_list);
        listView.addHeaderView(headView);

        mygoldtxt = headView.findViewById(R.id.sign_mygoldtxt);
        signBtn = headView.findViewById(R.id.signBtn);
        signwarmTxt = headView.findViewById(R.id.signwarmTxt);
        signInfoTxt = headView.findViewById(R.id.signInfoTxt);

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotosign();
            }
        });
    }

    public void gotosign() {
        Toast.makeText(this.getActivity(),"签到",Toast.LENGTH_SHORT).show();
        signItem.hasSign = true;
        updateHeadUI();
    }

    public void initData() {
        list = new ArrayList<ZhuanItem>();
        list.add(new ZhuanItem("完成运动","运动越多，成就越多","https://www.blocknew.net/img/portfolio/thumbnails/2.jpg",
                "run","100",false,0));
        list.add(new ZhuanItem("看视频奖励","精彩视频不断","https://www.blocknew.net/img/portfolio/thumbnails/2.jpg",
                "vedio","200",true,0));
        list.add(new ZhuanItem("分享APP","运动越多，成就越多","https://www.blocknew.net/img/portfolio/thumbnails/2.jpg",
                "run","100",true,0));
        list.add(new ZhuanItem("邀请好友","邀请好友奖励","https://www.blocknew.net/img/portfolio/thumbnails/2.jpg",
                "run","520",false,0));
        list.add(new ZhuanItem("看新闻给积分","海量新闻，积分狂送","https://www.blocknew.net/img/portfolio/thumbnails/2.jpg",
                "run","70",true,3));
        list.add(new ZhuanItem("完成个人资料","设置个人资料","https://www.blocknew.net/img/portfolio/thumbnails/2.jpg",
                "run","140",true,0));

        signItem = new SignItem("100",false);
    }

    public void setadapter() {
        adapter = new ZhuanAdapter(this.getActivity(),R.layout.zhuan_item,list);
        listView.setAdapter(adapter);

    }

    public void updateHeadUI() {
        //update headui
        if (signItem.hasSign == false ){
            signInfoTxt.setText("今日未签到");
            signwarmTxt.setText("签到可得到" + signItem.gold + "金币");
            signBtn.setBackgroundColor(getResources().getColor(R.color.color0000ff));
            signBtn.setEnabled(true);

        }else {
            signInfoTxt.setText("已签到");
            signwarmTxt.setText("签到奖励" + signItem.gold + "金币");
            signBtn.setBackgroundColor(getResources().getColor(R.color.color999999));
            signBtn.setEnabled(false);

        }
    }


}