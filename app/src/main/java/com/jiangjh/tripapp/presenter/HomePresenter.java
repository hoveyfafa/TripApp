package com.jiangjh.tripapp.presenter;

import com.jiangjh.tripapp.iview.IHomeView;

/**
 * Created by JiaHao.Huang on 2018/2/22.
 */

public class HomePresenter {

    private IHomeView mView;

    public HomePresenter(IHomeView view){
        this.mView = view;
    }
}
