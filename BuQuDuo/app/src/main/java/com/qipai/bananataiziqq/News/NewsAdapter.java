package com.qipai.bananataiziqq.News;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qipai.bananataiziqq.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewsAdapter extends ArrayAdapter {
    private int resourceId;

    NewsAdapter(Context context, int index, List<NewsItem>list){
        super(context,index,list);

        this.resourceId = index;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NewsViewHolder viewHolder;
        NewsItem item = (NewsItem)getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(resourceId,null);

            viewHolder = new NewsViewHolder();
            viewHolder.titleTxt = convertView.findViewById(R.id.textView_newstitle);
            viewHolder.coverImg = convertView.findViewById(R.id.imageView_news);
            viewHolder.haslookTxt = convertView.findViewById(R.id.textView_newslook);
            viewHolder.timeTxt = convertView.findViewById(R.id.textView_newstime);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (NewsViewHolder)convertView.getTag();
        }

        //update
        if (viewHolder != null){
            Log.d("title=",item.getTitle());
            viewHolder.titleTxt.setText(item.getTitle());
            viewHolder.timeTxt.setText(item.getPublishDateStr());
            viewHolder.haslookTxt.setText("" + item.getPosterScreenName());
            Glide.with(this.getContext()).load(item.getImageUrl()).placeholder(R.drawable.headimg).into(viewHolder.coverImg);
        }

        return convertView;
    }

    class NewsViewHolder {
        TextView titleTxt;
        ImageView coverImg;
        TextView haslookTxt;
        TextView timeTxt;

    }


}
