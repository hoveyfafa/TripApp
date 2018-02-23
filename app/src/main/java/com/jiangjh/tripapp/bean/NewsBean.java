package com.jiangjh.tripapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JiaHao.Huang on 2018/2/22.
 */

public class NewsBean implements Serializable{

    private int code;

    private String msg;

    private List<NewsListBean> newslist;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<NewsListBean> getNewslist() {
        return newslist;
    }
}
