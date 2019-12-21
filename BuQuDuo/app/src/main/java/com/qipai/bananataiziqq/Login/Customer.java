package com.qipai.bananataiziqq.Login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.qipai.bananataiziqq.Base.BaseItem;

import java.math.BigDecimal;

public class Customer extends BaseItem {
    private String access_token;
    private String token_type;

    private String name;
    private String avatar_url;
    private String phone;
    private String invitationCode;
    private Customer body;


    private int isWechat;
    private int gold;
    private BigDecimal amount;




    private String user_id;
    private int gender;
    private int age;
    private int height;
    private int weight;
    private int target_step;
    private float bml;


    private String nickname;
    private String headimgurl;


    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setIsWechat(int isWechat) {
        this.isWechat = isWechat;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getGold() {
        return gold;
    }

    public int getIsWechat() {
        return isWechat;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setBml(float bml) {
        this.bml = bml;
    }

    public void setBody(Customer body) {
        this.body = body;
    }


    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTarget_step(int target_step) {
        this.target_step = target_step;
    }

    public String getUser_id() {
        return user_id;
    }

    public int getWeight() {
        return weight;
    }

    public Customer getBody() {
        return body;
    }

    public int getAge() {
        return age;
    }

    public int getGender() {
        return gender;
    }

    public int getHeight() {
        return height;
    }

    public int getTarget_step() {
        return target_step;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public float getBml() {
        return bml;
    }





    //存储数据
    public static void saveCustomer(Context context,Customer customer) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserInfo",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name",customer.getName());
        editor.putString("avatar_url",customer.getAvatar_url());
        editor.putString("phone",customer.getPhone());
        editor.putString("user_id",customer.getUser_id());
        editor.putString("invitationCode",customer.getInvitationCode());
        editor.putInt("gender",customer.getGender());
        editor.putInt("weight",customer.getWeight());
        editor.putInt("height",customer.getHeight());
        editor.putInt("age",customer.getAge());
        editor.putInt("target_step",customer.getTarget_step());
        editor.putFloat("bml",customer.getBml());

        editor.commit();
    }

     public static Customer getCustomer(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserInfo",0);

        Customer item = new Customer();
        item.setName(sharedPreferences.getString("name","").toString());
        item.setAvatar_url(sharedPreferences.getString("avatar_url","").toString());
        item.setPhone(sharedPreferences.getString("phone","").toString());
        item.setInvitationCode(sharedPreferences.getString("invitationCode","").toString());
        item.setUser_id(sharedPreferences.getString("user_id","").toString());
        item.setAge(sharedPreferences.getInt("age",0));
        item.setGender(sharedPreferences.getInt("gender",0));
        item.setWeight(sharedPreferences.getInt("weight",0));
        item.setHeight(sharedPreferences.getInt("height",0));
        item.setTarget_step(sharedPreferences.getInt("target_step",0));
        item.setBml(sharedPreferences.getFloat("bml",0));

        return item;
    }


    public static void saveCustomer(Activity activity,Customer customer) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("UserInfo",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name",customer.getName());
        editor.putString("avatar_url",customer.getAvatar_url());
        editor.putString("phone",customer.getPhone());
        editor.putString("user_id",customer.getUser_id());
        editor.putString("invitationCode",customer.getInvitationCode());
        editor.putInt("gender",customer.getGender());
        editor.putInt("weight",customer.getWeight());
        editor.putInt("height",customer.getHeight());
        editor.putInt("age",customer.getAge());
        editor.putInt("target_step",customer.getTarget_step());
        editor.putFloat("bml",customer.getBml());

        editor.commit();
    }

    public static Customer getCustomer(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("UserInfo",0);

        Customer item = new Customer();
        item.setName(sharedPreferences.getString("name","").toString());
        item.setAvatar_url(sharedPreferences.getString("avatar_url","").toString());
        item.setPhone(sharedPreferences.getString("phone","").toString());
        item.setInvitationCode(sharedPreferences.getString("invitationCode","").toString());
        item.setUser_id(sharedPreferences.getString("user_id","").toString());
        item.setAge(sharedPreferences.getInt("age",0));
        item.setGender(sharedPreferences.getInt("gender",0));
        item.setWeight(sharedPreferences.getInt("weight",0));
        item.setHeight(sharedPreferences.getInt("height",0));
        item.setTarget_step(sharedPreferences.getInt("target_step",0));
        item.setBml(sharedPreferences.getFloat("bml",0));

        return item;
    }


    public static void clearCustomer(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("UserInfo",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }



}
