package com.example.buquduo.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.buquduo.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class UserActivity extends Fragment {

    View userView;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userView = inflater.inflate(R.layout.activity_user,container,false);

        initData();

        initView();

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

    }

    public void initView() {


        //点击事件
        addclick();
    }

    public void addclickId(){


    }


    public void addclick(){

        userView.findViewById(R.id.textView_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        userView.findViewById(R.id.button_copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        userView.findViewById(R.id.button_inputcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        userView.findViewById(R.id.image_head_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        userView.findViewById(R.id.image_head_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        userView.findViewById(R.id.textView_currentgold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        userView.findViewById(R.id.textView_rmb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        userView.findViewById(R.id.imageView_head).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });

    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.textView_name:
                Toast.makeText(this.getContext(),"点击登录",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_copy:
                Toast.makeText(this.getContext(),"复制",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_inputcode:
                Toast.makeText(this.getContext(),"输入邀请码",Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_head_msg:
                Toast.makeText(this.getContext(),"消息",Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_head_set:
                Toast.makeText(this.getContext(),"设置",Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView_currentgold:
                Toast.makeText(this.getContext(),"当前金币",Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView_rmb:
                Toast.makeText(this.getContext(),"金额",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_head:
                Toast.makeText(this.getContext(),"头像",Toast.LENGTH_SHORT).show();
                break;
        }
    }


}