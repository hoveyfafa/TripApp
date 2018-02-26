package com.jiangjh.tripapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.widget.TitleBar;

/**
 *
 * @author JiaHao.Huang
 * @date 2018/2/26
 */

public class SuggestionActivity extends AppCompatActivity{
    private EditText mEditText;
    private Button mButton;
    private TitleBar mTitleBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        initView();
        initListener();
    }

    private void initView(){
        mEditText = findViewById(R.id.edt_suggestion_content);
        mButton = findViewById(R.id.btn_suggestion);
        mTitleBar = findViewById(R.id.title_bar);
    }

    private void initListener(){
        mTitleBar.setLiftImageClickListener(new TitleBar.TitleBarLeftImageClickListener() {
            @Override
            public void onClickListener() {
                finish();
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
