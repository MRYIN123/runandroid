package com.example.buquduo.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.buquduo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewsAdapter extends ArrayAdapter {
    private int resourceId;

    NewsAdapter(Context context, int index, List<NewsItem>list){
        super(context,index,list);

        resourceId = index;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        NewsItem item = (NewsItem) getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(this.getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.titleTxt = view.findViewById(R.id.textView_newstitle);
            viewHolder.contentTxt = view.findViewById(R.id.textView_newscontent);
            viewHolder.coverImg = view.findViewById(R.id.imageView_news);
            viewHolder.haslookTxt = view.findViewById(R.id.textView_newslook);
            viewHolder.timeTxt = view.findViewById(R.id.textView_newstime);

        }else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //update
        viewHolder.titleTxt.setText(item.getTitle());
        viewHolder.contentTxt.setText(item.getContent());
        viewHolder.timeTxt.setText(item.getTime());
        viewHolder.haslookTxt.setText("已看：" + item.getHaslook());
        Glide.with(this.getContext()).load(item.getCover()).placeholder(R.drawable.headimg).into(viewHolder.coverImg);

        return view;
    }

    class ViewHolder {
        TextView titleTxt;
        TextView contentTxt;
        ImageView coverImg;
        TextView haslookTxt;
        TextView timeTxt;

    }


}
