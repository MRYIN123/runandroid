package com.example.buquduo.News;

import com.example.buquduo.Base.BaseItem;

public class NewsItem extends BaseItem {
    private String title;
    private String imageUrl;
    private String content;
    private String url;
    private String publishDateStr;
    private String posterScreenName;
    private String publishDateShowStr;
    private String key;
    private String value;



    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPosterScreenName(String posterScreenName) {
        this.posterScreenName = posterScreenName;
    }

    public void setPublishDateStr(String publishDateStr) {
        this.publishDateStr = publishDateStr;
    }

    public void setPublishDateShowStr(String publishDateShowStr) {
        this.publishDateShowStr = publishDateShowStr;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        if (url == null) {
            return "";
        }
        return url;
    }

    public String getImageUrl() {
        if (imageUrl == null) {
            return "";
        }
        return imageUrl;
    }

    public String getPublishDateStr() {
        if (publishDateStr == null) {
            return "";
        }
        return publishDateStr;
    }

    public String getPosterScreenName() {
        if (posterScreenName == null) {
            return "";
        }
        return posterScreenName;
    }

    public String getPublishDateShowStr() {
        return publishDateShowStr;
    }


    public String getContent() {
        if (content == null) {
            return "";
        }
        return content;
    }



    public String getTitle() {
        if (title == null) {
            return "";
        }
        return title;
    }


    NewsItem(String title,String content,String url,String imageUrl,String id){
        this.url = url;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.id = id;

    }


    NewsItem(String title,String content,String url,String imageUrl,String id,String posterScreenName,String publishDateStr){
        this.url = url;
        this.posterScreenName = posterScreenName;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.id = id;
        this.publishDateStr = publishDateStr;

    }



}
