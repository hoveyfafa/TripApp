package com.jiangjh.tripapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.widget.TitleBar;

/**
 * @author Jinghao.Jiang
 * @date 2018/2/20
 */

public class UserFragment extends Fragment {

    private TitleBar mTitleBar;
    private View mView;

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_user, null);
        findViews();
        showTitleStyle();
        return mView;
    }

    private void showTitleStyle() {
        mTitleBar = mView.findViewById(R.id.title_bar);
        mTitleBar.hiddenLeftText();

    }

    private void findViews() {

    }
}
