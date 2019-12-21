package com.qipai.bananataiziqq.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qipai.bananataiziqq.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserAdapter extends ArrayAdapter {

    private int resourceId;


    public UserAdapter(Context context, int resource, List<ViewItems>objs) {
        super(context,resource,objs);

        resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        View view;
        ViewItems viewItems = (ViewItems) getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.leftTxt = view.findViewById(R.id.useritem_txt);
            viewHolder.leftImg = view.findViewById(R.id.useritem_img);
            view.setTag(viewHolder);

        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.leftTxt.setText(viewItems.getLeftTitle());
        viewHolder.leftImg.setImageResource(viewItems.getLeftImg());

        return view;
    }

    class ViewHolder{
        ImageView leftImg;
        TextView leftTxt;
    }


}
