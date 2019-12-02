package com.example.buquduo.News;

import com.example.buquduo.Base.BaseItem;

public class NewsItem extends BaseItem {
    private String title;
    private String cover;
    private String content;
    private String target;
    private String haslook;
    private String from;
    private String time;

    public void setContent(String content) {
        this.content = content;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setHaslook(String haslook) {
        this.haslook = haslook;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public String getCover() {
        return cover;
    }

    public String getFrom() {
        return from;
    }

    public String getHaslook() {
        return haslook;
    }

    public String getTarget() {
        return target;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }


    NewsItem(String title,String content,String target,String cover,String id){
        this.target = target;
        this.title = title;
        this.content = content;
        this.cover = cover;
        this.id = id;

    }


    NewsItem(String title,String content,String target,String cover,String id,String time,String haslook){
        this.target = target;
        this.time = time;
        this.title = title;
        this.content = content;
        this.cover = cover;
        this.id = id;
        this.haslook = haslook;

    }



}
