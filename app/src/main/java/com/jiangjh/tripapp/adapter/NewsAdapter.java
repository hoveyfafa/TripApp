package com.jiangjh.tripapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.bean.NewsListBean;
import com.jiangjh.tripapp.util.Misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jinghao.Jiang on 2018/2/24.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {
    private List<NewsListBean> mList;
    private Context mContext;
    private OnNewsItemClickListener mOnNewsItemClickListener;

    public NewsAdapter(List<NewsListBean> list, Context context) {
        this.setListData(list);
        this.mContext = context;
    }

    public void setOnNewsItemClickListener(OnNewsItemClickListener onNewsItemClickListener) {
        this.mOnNewsItemClickListener = onNewsItemClickListener;
    }

    public void setListData(List<NewsListBean> list) {
        if (mList != null) {
            mList.clear();
        } else {
            mList = new ArrayList<>();
        }
        if (list != null) {
            mList.addAll(list);
        }

        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_news_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final NewsListBean item = mList.get(position);

        if (!TextUtils.isEmpty(item.getTitle())) {
            holder.mTitle.setText(item.getTitle());
        }
        if (!TextUtils.isEmpty(item.getDescription())) {
            holder.mDescription.setText(item.getDescription());
        }
        if (!TextUtils.isEmpty(item.getCtime())) {
            holder.mTime.setText(item.getCtime());
        }
        if (!TextUtils.isEmpty(item.getPicUrl())) {
            Misc.display(mContext, item.getPicUrl(), holder.mShowImg);
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnNewsItemClickListener.onItem(item);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class Holder extends RecyclerView.ViewHolder {
        TextView mTitle, mDescription, mTime;
        ImageView mShowImg;
        View mView;

        Holder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_title_news_item);
            mDescription = itemView.findViewById(R.id.tv_description_news_item);
            mTime = itemView.findViewById(R.id.tv_time_news_item);
            mShowImg = itemView.findViewById(R.id.iv_news_item);
            mView = itemView;
        }
    }

    public interface OnNewsItemClickListener {

        void onItem(NewsListBean info);
    }
}
