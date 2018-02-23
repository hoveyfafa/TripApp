package com.jiangjh.tripapp.bean;

import java.io.Serializable;

/**
 * Created by JiaHao.Huang on 2018/2/22.
 */

public class NewsListBean implements Serializable{

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
}
