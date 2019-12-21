package com.qipai.bananataiziqq.ZhuanZhuan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qipai.bananataiziqq.R;
import com.qipai.bananataiziqq.User.RunHistoryActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ZhuanAdapter extends ArrayAdapter {

    private int resourceId;
    ZhuanAdapter(Context context,int resourceId, ArrayList<ZhuanItem>list) {
        super(context,resourceId,list);

        this.resourceId = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ZhuanViewHolder viewHolder;
        final ZhuanItem zhuanItem = (ZhuanItem) getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(this.getContext()).inflate(resourceId,parent,false);
            viewHolder = new ZhuanViewHolder();
            viewHolder.titleTxt = view.findViewById(R.id.zhuanitem_title);
            viewHolder.titledestxt = view.findViewById(R.id.zhuanitem_titledes);
            viewHolder.contentTxt = view.findViewById(R.id.zhuanitem_content);
            viewHolder.coinTxt = view.findViewById(R.id.zhuanitem_coin);
            viewHolder.thumb = view.findViewById(R.id.zhuanitem_logo);
            viewHolder.button = view.findViewById(R.id.zhuanitem_btn);

        }else {
            view = convertView;
            viewHolder = (ZhuanViewHolder)view.getTag();
        }

        viewHolder.titleTxt.setText(zhuanItem.getTitle());
        viewHolder.contentTxt.setText(zhuanItem.getContext());
        viewHolder.coinTxt.setText( "+" + zhuanItem.getAmount());
//        viewHolder.titledestxt.setText(String.valueOf(zhuanItem.getCompleted()));

        /**
         *  **可完成次数: 0代表可无限完成 没有限制frequency;
         *  *完成次数 completed;
         * */
        if (zhuanItem.isFinish()) {
                viewHolder.button.setText("立即领取");
                viewHolder.button.setBackgroundResource(R.mipmap.cangebtn);
        }else {
                viewHolder.button.setText("去完成");
                viewHolder.button.setBackgroundResource(R.mipmap.unfinishbtn);
        }


        Glide.with(this.getContext()).load(zhuanItem.getImage_url()).placeholder(R.drawable.headimg).into(viewHolder.thumb);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchZhuanBtn(zhuanItem);
            }
        });

        return view;
    }

    public void touchZhuanBtn(ZhuanItem item) {
//        if (item.type == "run") {
//            Intent intent = new Intent();
//            intent.setClass(this.getContext(),RunHistoryActivity.class);
//            this.getContext().startActivity(intent);
//
//        }else if (item.type == "vedio"){
//            Toast.makeText(this.getContext(),"看视频啊",Toast.LENGTH_SHORT).show();
//
//        }

        Toast.makeText(this.getContext(),item.getTitle(),Toast.LENGTH_SHORT).show();
    }

    class ZhuanViewHolder {
        TextView titleTxt;
        TextView titledestxt;
        TextView contentTxt;
        ImageView thumb;
        Button button;
        TextView coinTxt;

    }



}
