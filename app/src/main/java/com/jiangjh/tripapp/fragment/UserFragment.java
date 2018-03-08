package com.jiangjh.tripapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.activity.LoginActivity;
import com.jiangjh.tripapp.activity.SuggestionActivity;
import com.jiangjh.tripapp.widget.CircleImageView;
import com.jiangjh.tripapp.widget.TitleBar;

/**
 * @author Jinghao.Jiang
 * @date 2018/2/20
 */

public class UserFragment extends Fragment {

    private TitleBar mTitleBar;
    private View mView;
    private CircleImageView mCircleImageView;
    private RelativeLayout mSuggestionLayout;
    private TextView mAccount;
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
        initListener();
        return mView;
    }

    private void showTitleStyle() {
        mTitleBar = mView.findViewById(R.id.title_bar);
        mTitleBar.hiddenLeftText();

    }

    private void findViews() {
        mCircleImageView = mView.findViewById(R.id.circleImageView);
        mSuggestionLayout = mView.findViewById(R.id.rl_suggestion_mine);
        mAccount = mView.findViewById(R.id.tv_mine_user_name);
        getAccountName();
    }

    private void initListener(){
        mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        mSuggestionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SuggestionActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        getAccountName();
    }

    private void getAccountName(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("account",0);
        String name = sharedPreferences.getString("name","默认用户");
        mAccount.setText(name);
    }
}
