package com.jiangjh.tripapp.fragment;

import android.os.Bundle;
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

public class DestinationFragment extends Fragment {
    private View mView;
    private TitleBar titleBar;

    public static DestinationFragment newInstance() {
        DestinationFragment fragment = new DestinationFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_destination, null);
        showTitleStyle();
        return mView;
    }

    private void showTitleStyle() {
        titleBar = mView.findViewById(R.id.title_bar);
        titleBar.hiddenLeftText();
    }
}
