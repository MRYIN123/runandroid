package com.example.buquduo.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.buquduo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GoldRecordAdapter extends ArrayAdapter {

    private int resourceId;
    GoldRecordAdapter(Context context, int resourceId, ArrayList<GoldItem>list){
        super(context,resourceId,list);

        this.resourceId = resourceId;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        GoldRecordHolder holder;
        GoldItem item = (GoldItem)getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(this.getContext()).inflate(resourceId,parent,false);
            holder = new GoldRecordHolder();

            holder.timeTxt = view.findViewById(R.id.txt_goldrecord_title);
            holder.eventTxt = view.findViewById(R.id.txt_goldrecord_content);
            holder.goldTxt = view.findViewById(R.id.txt_goldrecord_gold);

        }else {
            view = convertView;
            holder = (GoldRecordHolder)view.getTag();
        }

        holder.timeTxt.setText(item.getCreate_time());
        holder.goldTxt.setText(item.getGold());
        holder.eventTxt.setText(item.getEvent());

        return  view;

    }

    class GoldRecordHolder{
        TextView timeTxt;
        TextView goldTxt;
        TextView eventTxt;
    }
}
