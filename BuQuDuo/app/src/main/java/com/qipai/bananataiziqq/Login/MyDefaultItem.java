package com.qipai.bananataiziqq.Login;

import android.content.Context;
import android.content.SharedPreferences;

import com.qipai.bananataiziqq.Base.BaseItem;

public class MyDefaultItem extends BaseItem {


    private String bindflag;



    public String getBindflag() {
        return bindflag;
    }

    public void setBindflag(String bindflag) {
        this.bindflag = bindflag;
    }








    //存储数据
    public static void saveMyDefaultItem(Context context, MyDefaultItem item) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyDefault", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("bindflag", item.getBindflag());
        editor.commit();

    }

    public static MyDefaultItem getMyDefaultItem(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyDefault",0);

        MyDefaultItem item = new MyDefaultItem();
        item.setBindflag(sharedPreferences.getString("bindflag","").toString());


        return item;
    }
}
