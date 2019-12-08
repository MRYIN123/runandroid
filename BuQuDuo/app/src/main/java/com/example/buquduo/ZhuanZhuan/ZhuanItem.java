package com.example.buquduo.ZhuanZhuan;

import com.example.buquduo.Base.BaseItem;


//public enum  ZhuanItemMenu {
//    RunType,
//    VedioType
//}

public class ZhuanItem extends BaseItem {

    public String type;
    public String logo;
    public String title;
    public String content;
    public String gold;
    public Boolean finish;
    public int hasfinish;

    ZhuanItem(String title,String content,String logo,String type,String gold,Boolean finish,int hasfinish){
        this.title = title;
        this.content = content;
        this.logo = logo;
        this.type = type;
        this.gold = gold;
        this.finish = finish;
        this.hasfinish = hasfinish;

    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public void setHasfinish(int hasfinish) {
        this.hasfinish = hasfinish;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getGold() {
        return gold;
    }

    public Boolean getFinish() {
        return finish;
    }

    public String getLogo() {
        return logo;
    }

    public String getTitle() {
        return title;
    }

    public int getHasfinish() {
        return hasfinish;
    }

    public String getType() {
        return type;
    }


}