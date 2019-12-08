package com.example.buquduo.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buquduo.R;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class SetAdapter extends ArrayAdapter {

    private int resouceId;

    public SetAdapter(Context context, int resId, List<ViewItems>list){
        super(context,resId,list);

        this.resouceId = resId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        SetViewHodler viewHodler;
        ViewItems viewItems = (ViewItems) getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resouceId,parent,false);
            viewHodler = new SetViewHodler();
            viewHodler.infotxt = view.findViewById(R.id.setitem_txt);

        }else {
            view = convertView;
            viewHodler = (SetViewHodler) view.getTag();
        }

        viewHodler.infotxt.setText(viewItems.getLeftTitle());

        return view;
    }

    class SetViewHodler {
        TextView infotxt;
    }
}
