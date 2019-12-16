package com.example.buquduo.Home;

import com.example.buquduo.Base.BaseItem;

public class BannerItem extends BaseItem {

    public String status;
    public String image_url;
    public int image_id;
    public int weight;
    public String url;
    public BannerSubItem get_image;


    public void setStatus(String status) {
        this.status = status;
    }

    public void setGet_image(BannerSubItem get_image) {
        this.get_image = get_image;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public BannerSubItem getGet_image() {
        return get_image;
    }

    public int getImage_id() {
        return image_id;
    }

    public int getWeight() {
        return weight;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getUrl() {
        return url;
    }

}
