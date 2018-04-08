package com.jiangjh.tripapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.activity.WebActivity;
import com.jiangjh.tripapp.widget.TitleBar;

/**
 * @author Jinghao.Jiang
 * @date 2018/2/20
 */

public class DestinationFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private TitleBar titleBar;
    private RelativeLayout mDestinationOne,mDestinationTwo,mDestinationThree;
    public static DestinationFragment newInstance() {
        DestinationFragment fragment = new DestinationFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_destination, null);
        showTitleStyle();
        initView();
        initListener();
        return mView;
    }

    private void showTitleStyle() {
        titleBar = mView.findViewById(R.id.title_bar);
        titleBar.hiddenLeftText();
    }

    private void initView(){
        mDestinationOne = mView.findViewById(R.id.rl_destination_one);
        mDestinationTwo = mView.findViewById(R.id.rl_destination_two);
        mDestinationThree = mView.findViewById(R.id.rl_destination_three);
    }

    private void initListener(){
        mDestinationOne.setOnClickListener(this);
        mDestinationTwo.setOnClickListener(this);
        mDestinationThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_destination_one:
                goToWebActivity("http://www.mafengwo.cn/travel-scenic-spot/mafengwo/10065.html");
                break;
            case R.id.rl_destination_two:
                goToWebActivity("http://www.mafengwo.cn/travel-scenic-spot/mafengwo/12700.html");
                break;
            case R.id.rl_destination_three:
                goToWebActivity("http://www.mafengwo.cn/travel-scenic-spot/mafengwo/11045.html");
                break;
                default:
                    break;
        }
    }

    public void goToWebActivity(String url){
        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.putExtra("webUrl",url);
        startActivity(intent);
    }
}
