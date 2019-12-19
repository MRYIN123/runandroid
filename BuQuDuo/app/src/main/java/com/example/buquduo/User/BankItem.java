package com.example.buquduo.User;

import com.example.buquduo.Base.BaseItem;

public class BankItem extends BaseItem {

    public String bank_name;
    public String bank_code;
    public int status;



    BankItem() {

    }

    BankItem(String bank_code,String bank_name,String id,int status){
        this.bank_code = bank_code;
        this.bank_name = bank_name;
        this.id = id;
        this.status = status;

    }


    public void setStatus(int status) {
        this.status = status;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public int getStatus() {
        return status;
    }

    public String getBank_code() {
        return bank_code;
    }

    public String getBank_name() {
        return bank_name;
    }


}
