package com.jiangjh.tripapp.iview;

import com.jiangjh.tripapp.adapter.NewsAdapter;

/**
 * Created by JiaHao.Huang on 2018/2/22.
 */

public interface IHomeView {

    void getNewsFail();

    void getNewsSuccess(NewsAdapter newsAdapter);
}
