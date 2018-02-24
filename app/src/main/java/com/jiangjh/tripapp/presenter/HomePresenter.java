package com.jiangjh.tripapp.presenter;

import android.content.Context;
import android.content.Intent;

import com.jiangjh.tripapp.activity.WebActivity;
import com.jiangjh.tripapp.adapter.NewsAdapter;
import com.jiangjh.tripapp.bean.NewsBean;
import com.jiangjh.tripapp.bean.NewsListBean;
import com.jiangjh.tripapp.iview.IHomeView;
import com.jiangjh.tripapp.request.RequestUrl;
import com.jiangjh.tripapp.util.HttpUtil;
import com.jiangjh.tripapp.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author Jinghao.Jiang
 * @date 2018/2/22
 */

public class HomePresenter {

    private IHomeView mView;
    private Context mContext;
    private NewsAdapter mAdapter;

    public HomePresenter(IHomeView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public void getTripNews() {
        HttpUtil.sendOkHttpRequest(RequestUrl.IP, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mView.getNewsFail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final NewsBean newsBean = Utility.parseJsonWithGson(responseText);
                if (200 == newsBean.getCode() && newsBean.getNewslist() != null) {
                    if (mAdapter == null) {
                        mAdapter = new NewsAdapter(newsBean.getNewslist(), mContext);
                    }
                    mAdapter.setListData(newsBean.getNewslist());
                    mAdapter.setOnNewsItemClickListener(new NewsAdapter.OnNewsItemClickListener() {
                        @Override
                        public void onItem(NewsListBean info) {
                            Intent intent = new Intent(mContext, WebActivity.class);
                            intent.putExtra("webUrl", info.getUrl());
                            mContext.startActivity(intent);
                        }
                    });
                    mView.getNewsSuccess(mAdapter);
                } else {
                    mView.getNewsFail();
                }
            }
        });
    }
}
