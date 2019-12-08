package com.example.buquduo.User;

import com.example.buquduo.Base.BaseItem;

public class GoldItem extends BaseItem {
    public String gold;
    public String event;
    public String user_id;
    public String create_at;
    public String updated_at;
    public String amount;
    public String status;
    public String create_day;
    public String create_time;

    public String totalAmount;
    public String todayAmount;


    GoldItem(String gold,String event,String create_at) {
        this.gold = gold;
        this.event = event;
        this.create_at = create_at;
    }

    GoldItem(String amount,String status) {
        this.amount = amount;
        this.status = status;
    }

    GoldItem(String todayAmount,String totalAmount,int type){
        this.todayAmount = todayAmount;
        this.totalAmount = totalAmount;
    }


    public void setTodayAmount(String todayAmount) {
        this.todayAmount = todayAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCreate_day(String create_day) {
        this.create_day = create_day;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGold() {
        return gold;
    }

    public String getAmount() {
        return amount;
    }

    public String getCreate_at() {
        return create_at;
    }

    public String getEvent() {
        return event;
    }

    public String getStatus() {
        return status;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCreate_day() {
        return create_day;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getTodayAmount() {
        return todayAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }
}
