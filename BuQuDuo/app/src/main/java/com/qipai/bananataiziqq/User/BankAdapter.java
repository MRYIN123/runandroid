package com.qipai.bananataiziqq.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.qipai.bananataiziqq.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BankAdapter extends ArrayAdapter {

    private int resouceId;

    public BankAdapter(Context context, int resId, List<BankItem> list){
        super(context,resId,list);

        this.resouceId = resId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        BankViewHolder viewHodler;
        BankItem viewItems = (BankItem) getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resouceId,parent,false);
            viewHodler = new BankViewHolder();
            viewHodler.textView = view.findViewById(R.id.bankitem_txt);

        }else {
            view = convertView;
            viewHodler = (BankViewHolder) view.getTag();
        }

        viewHodler.textView.setText(viewItems.getBank_name());

        return view;
    }


    class BankViewHolder{
        TextView textView;

    }

}
