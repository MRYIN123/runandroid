package com.qipai.bananataiziqq.User;

public class ViewItems {

    private String leftTitle;
    private int leftImg;

    ViewItems(String leftTitle,int leftImg) {
        this.leftImg = leftImg;
        this.leftTitle = leftTitle;
    }

    ViewItems(String leftTitle){
        this.leftTitle = leftTitle;
    }

    public void setLeftImg(int leftImg) {
        this.leftImg = leftImg;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    public int getLeftImg() {
        return leftImg;
    }

    public String getLeftTitle() {
        return leftTitle;
    }
}
