package com.qipai.bananataiziqq.ZhuanZhuan;

import com.qipai.bananataiziqq.Base.BaseItem;
import com.qipai.bananataiziqq.R;


//public enum  ZhuanItemMenu {
//    RunType,
//    VedioType
//}

public class ZhuanItem extends BaseItem {

    public int event;
    public String image_url;
    public String title;
    public String context;
    public String amount;

    /***可完成次数: 0代表可无限完成 没有限制*/
    public int frequency;
    /**完成次数*/
    public int completed;




    ZhuanItem(String title,String context,String image_url,int event,String amount,int frequency,int completed){
        this.title = title;
        this.context = context;
        this.image_url = image_url;
        this.event = event;
        this.amount = amount;
        this.frequency = frequency;
        this.completed = completed;

    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getImage_url() {
        return image_url;
    }

    public int getEvent() {
        return event;
    }

    public String getAmount() {
        return amount;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getCompleted() {
        return completed;
    }

    public String getContext() {
        return context;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    public Boolean isFinish() {
        /**
         *  **可完成次数: 0代表可无限完成 没有限制frequency;
         *  *完成次数 completed;
         * */
        if (getFrequency() == 0) {
            if (getCompleted() > getFrequency()){
                return true;
            }else {
                return false;
            }

        }else {
            if (getCompleted() > getFrequency()){
                return true;
            }else {
                return false;
            }

        }
    }

}