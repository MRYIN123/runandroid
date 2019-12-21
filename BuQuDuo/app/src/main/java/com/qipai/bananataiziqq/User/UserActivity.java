package com.qipai.bananataiziqq.User;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.qipai.bananataiziqq.Login.LoginFirstActivity;
import com.qipai.bananataiziqq.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class UserActivity extends Fragment {

    View userView;
    ListView listView;
    View headView;
    ArrayList<ViewItems> datalist;
    UserAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userView = inflater.inflate(R.layout.activity_user,container,false);

        initData();

        initView();

        setAdapter();

        return userView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    public void initData() {
        datalist = new ArrayList<ViewItems>();
        datalist.add(new ViewItems("邀请好友",R.mipmap.aixin));
//        datalist.add(new ViewItems("个人成就",R.drawable.chengjiu));
        datalist.add(new ViewItems("邀请码",R.mipmap.yaoqingma));
        datalist.add(new ViewItems("绑定账户",R.mipmap.bangding));
        datalist.add(new ViewItems("步数记录",R.mipmap.jilu));
        datalist.add(new ViewItems("身体数据",R.mipmap.jiankangcard));
//        datalist.add(new ViewItems("我的客服",R.mipmap.kefu));

    }

    public void setAdapter() {
        adapter = new UserAdapter(this.getActivity(),R.layout.useritem,datalist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotonext(position);
            }
        });
    }

    public void gotonext(int postion) {
        ViewItems items = (ViewItems)datalist.get(postion - 1);

        if (items.getLeftTitle() == "邀请好友") {
            Toast.makeText(this.getContext(),"邀请好友",Toast.LENGTH_SHORT).show();

        }else if (items.getLeftTitle() == "身体数据") {
            Intent intent = new Intent();
            intent.setClass(this.getActivity(),HealthActivity.class);
            this.startActivity(intent);

        }else if (items.getLeftTitle() == "步数记录") {
            Intent intent = new Intent();
            intent.setClass(this.getActivity(),RunHistoryActivity.class);
            this.startActivity(intent);

        }else if (items.getLeftTitle() == "个人成就") {
            Toast.makeText(this.getContext(),"个人成就",Toast.LENGTH_SHORT).show();

        }else if (items.getLeftTitle() == "我的客服") {
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + "17521011606");
            intent.setData(data);
            startActivity(intent);

        }else if (items.getLeftTitle() == "绑定账户") {
            Intent intent = new Intent();
            intent.setClass(this.getContext(),BindAccountActivity.class);
            this.startActivity(intent);

        }else if (items.getLeftTitle() == "邀请码") {
            Intent intent = new Intent();
            intent.setClass(this.getContext(),InvitationCodeActivity.class);
            startActivity(intent);
        }

    }

    public void initView() {
        headView = getLayoutInflater().inflate(R.layout.userhead,null);
        listView = userView.findViewById(R.id.userlist);
        listView.addHeaderView(headView);

        //add click
        addheadclick(R.id.textView_name);
//        addheadclick(R.id.button_copy);
//        addheadclick(R.id.button_inputcode);
        addheadclick(R.id.image_head_set);
        addheadclick(R.id.textView_currentgold);
        addheadclick(R.id.textView_rmb);
        addheadclick(R.id.imageView_head);
    }

    public void addheadclick(int name){
        userView.findViewById(name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.imageView_head:
            case R.id.textView_name:
                gotologin();
                break;
//            case R.id.button_copy:
//                copyaction();
//                break;
//            case R.id.button_inputcode:
//                Toast.makeText(this.getContext(),"输入邀请码",Toast.LENGTH_SHORT).show();
//                break;
            case R.id.image_head_set:
                gotoset();
                break;
            case R.id.textView_currentgold:
                gotogold();
            case R.id.textView_rmb:
                gotowithdraw();
                break;
                default:break;
        }
    }

    public void copyaction() {
        Toast.makeText(this.getContext(),"复制",Toast.LENGTH_SHORT).show();
    }

    public void gotologin() {
        Intent intent = new Intent();
        intent.setClass(this.getContext(), LoginFirstActivity.class);
        startActivity(intent);
    }

    public void gotoset() {
        Intent intent = new Intent();
        intent.setClass(this.getContext(),SetActivity.class);
        startActivity(intent);
    }

    public void gotogold() {
        Intent intent = new Intent();
        intent.setClass(this.getContext(),GoldRecordActivity.class);
        startActivity(intent);
    }

    public void gotowithdraw() {
        Intent intent = new Intent();
        intent.setClass(this.getContext(),WithDrawActivity.class);
        startActivity(intent);
    }







}