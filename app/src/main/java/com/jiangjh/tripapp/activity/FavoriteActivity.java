package com.jiangjh.tripapp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.adapter.NewsAdapter;
import com.jiangjh.tripapp.bean.NewsListBean;
import com.jiangjh.tripapp.database.FavoriteDBOpenHelper;
import com.jiangjh.tripapp.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JingHao.Jiang
 * @date 2018/4/24
 */

public class FavoriteActivity extends AppCompatActivity{
    private TitleBar mTitleBar;
    private RecyclerView mRecyclerView;
    private FavoriteDBOpenHelper mFavoriteDBOpenHelper;
    private NewsAdapter mAdapter;
    private List<NewsListBean> mList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        initView();
    }

    private void initView(){
        mTitleBar = findViewById(R.id.title_bar);
        mRecyclerView = findViewById(R.id.recycler_view);
        mTitleBar.setLiftImageClickListener(new TitleBar.TitleBarLeftImageClickListener() {
            @Override
            public void onClickListener() {
                finish();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mFavoriteDBOpenHelper = new FavoriteDBOpenHelper(this, "jjh.db", null, 1);
        initFavoriteDB();
        if (mList !=null && mList.size() >0){
            mAdapter = new NewsAdapter(mList,this);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnNewsItemClickListener(new NewsAdapter.OnNewsItemClickListener() {
                @Override
                public void onItem(NewsListBean info) {
                    Intent intent = new Intent(FavoriteActivity.this, WebActivity.class);
                    intent.putExtra("webUrl", info.getUrl());
                    intent.putExtra("news",info);
                    startActivity(intent);
                }
            });
        }
    }

    private void initFavoriteDB(){
        mList = new ArrayList<>();
        mList.clear();
        SQLiteDatabase database = mFavoriteDBOpenHelper.getReadableDatabase();
        Cursor cursor = database.query("favorite",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            for (int i = 0;i < cursor.getCount();i++){
                cursor.move(i);
                int id = cursor.getInt(0);
                String time = cursor.getString(1);
                String title = cursor.getString(2);
                String description = cursor.getString(3);
                String picurl= cursor.getString(4);
                String url = cursor.getString(5);
                NewsListBean listBean = new NewsListBean();
                listBean.setCtime(time);
                listBean.setTitle(title);
                listBean.setDescription(description);
                listBean.setPicUrl(picurl);
                listBean.setUrl(url);
                mList.add(i,listBean);
            }
        }
    }
}
