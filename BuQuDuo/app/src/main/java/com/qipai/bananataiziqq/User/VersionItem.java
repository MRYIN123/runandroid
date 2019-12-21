package com.qipai.bananataiziqq.User;

import com.qipai.bananataiziqq.Base.BaseItem;

public class VersionItem extends BaseItem {
    private String newVersion;
    private String minVersion;
    private String apkUrl;
    private String updateDescription;
    private int platform;
    private int isUpdate;
    private int forceUpdate;



    VersionItem(String newVersion,String minVersion,String updateDescription,String apkUrl,int platform,int isUpdate,int forceUpdate) {
        this.newVersion = newVersion;
        this.apkUrl = apkUrl;
        this.forceUpdate = forceUpdate;
        this.isUpdate = isUpdate;
        this.platform = platform;
        this.updateDescription = updateDescription;
        this.minVersion = minVersion;

    }




    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public void setForceUpdate(int forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
    }

    public int getForceUpdate() {
        return forceUpdate;
    }

    public int getIsUpdate() {
        return isUpdate;
    }

    public int getPlatform() {
        return platform;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public String getMinVersion() {
        return minVersion;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public String getUpdateDescription() {
        return updateDescription;
    }



}
