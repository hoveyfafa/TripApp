package com.jiangjh.tripapp.util;

import com.google.gson.Gson;
import com.jiangjh.tripapp.bean.NewsBean;

/**
 *
 * @author Jinghao.Jiang
 * @date 2018/2/24
 */

public class Utility {

    public static NewsBean parseJsonWithGson(final String requestText) {
        Gson gson = new Gson();
        return gson.fromJson(requestText, NewsBean.class);
    }
}
