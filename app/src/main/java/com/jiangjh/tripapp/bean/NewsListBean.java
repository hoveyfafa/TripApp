package com.jiangjh.tripapp.bean;

import java.io.Serializable;

/**
 * Created by Jinghao.Jiang on 2018/2/22.
 */

public class NewsListBean implements Serializable {

    private String ctime;

    private String title;

    private String description;

    private String picUrl;

    private String url;

    public String getCtime() {
        return ctime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
