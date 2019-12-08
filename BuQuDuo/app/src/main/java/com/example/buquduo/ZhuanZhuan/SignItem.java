package com.example.buquduo.ZhuanZhuan;

import com.example.buquduo.Base.BaseItem;

public class SignItem extends BaseItem {

    public String gold;
    public Boolean hasSign;


    SignItem(String gold,Boolean hasSign){
        this.gold = gold;
        this.hasSign = hasSign;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public void setHasSign(Boolean hasSign) {
        this.hasSign = hasSign;
    }

    public String getGold() {
        return gold;
    }

    public Boolean getHasSign() {
        return hasSign;
    }
}
