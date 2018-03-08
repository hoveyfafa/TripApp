package com.jiangjh.tripapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.adapter.NewsAdapter;
import com.jiangjh.tripapp.iview.IHomeView;
import com.jiangjh.tripapp.presenter.HomePresenter;
import com.jiangjh.tripapp.widget.TitleBar;

/**
 * @author Jinghao.Jiang
 * @date 2018/2/20
 */

public class HomeFragment extends Fragment implements IHomeView {
    private View mView;
    private TitleBar titleBar;
    private RecyclerView mRecyclerView;
    private HomePresenter mPresenter;
    private Context mContext;
    private SwipeRefreshLayout swipeRefresh;
    private NewsAdapter mAdapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, null);
        mPresenter = new HomePresenter(this, mContext);
        showTitleStyle();
        findViews();
        mPresenter.getTripNews();
        return mView;
    }

    private void findViews() {
        mRecyclerView = mView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        swipeRefresh = mView.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.baseColor);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getTripNews();
            }
        });
    }

    private void showTitleStyle() {
        titleBar = mView.findViewById(R.id.title_bar);
        titleBar.hiddenLeftText();
    }

    @Override
    public void getNewsFail() {
        uiHandler.sendEmptyMessage(1);

    }

    @Override
    public void getNewsSuccess(NewsAdapter newsAdapter) {
        mAdapter = newsAdapter;

        uiHandler.sendEmptyMessage(0);

    }

    public Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    swipeRefresh.setRefreshing(false);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    swipeRefresh.setRefreshing(false);
                    Toast.makeText(mContext, "获取新闻失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            return false;
        }
    });
}
