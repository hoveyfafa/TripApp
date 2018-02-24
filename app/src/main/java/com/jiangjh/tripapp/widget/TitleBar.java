package com.jiangjh.tripapp.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiangjh.tripapp.R;

/**
 *
 * @author Jinghao.Jiang
 * @date 2018/2/24
 */

public class TitleBar extends RelativeLayout implements View.OnClickListener {

    private TextView mLeftText;
    private TextView mTitle;
    private ImageView mRightButton;
    private ImageView mLeftButton;
    private TextView mRightText;

    private CharSequence mTitleCharSequence;
    private Drawable mRightDrawable;
    private Drawable mLeftDrawable;
    private CharSequence mRightCharSequence;
    private CharSequence mLeftCharSequence;

    public TitleBar(Context context) {
        super(context);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
        initView(context);
    }

    private void parseAttributes(Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.titleBar);
            mTitleCharSequence = typedArray.getText(R.styleable.titleBar_titleText);
            mRightDrawable = typedArray.getDrawable(R.styleable.titleBar_rightDrawable);
            mLeftDrawable = typedArray.getDrawable(R.styleable.titleBar_leftDrawable);
            mRightCharSequence = typedArray.getText(R.styleable.titleBar_rightText);
            mLeftCharSequence = typedArray.getText(R.styleable.titleBar_leftText);
            typedArray.recycle();
        }
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_title_bar, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        findView();
        updateView();
        initListener();

    }

    private void findView() {
        mLeftText = (TextView) findViewById(R.id.title_bar_left_back_text);
        mTitle = (TextView) findViewById(R.id.title_bar_text);
        mRightButton = (ImageView) findViewById(R.id.title_bar_right_image);
        mLeftButton = (ImageView) findViewById(R.id.title_bar_left_image);
        mRightText = (TextView) findViewById(R.id.title_bar_right_text);
    }

    private void updateView() {

        if (!TextUtils.isEmpty(mTitleCharSequence)) {
            setTitle(mTitleCharSequence);
        }

        if (mRightDrawable != null) {
            setRightDrawable(mRightDrawable);
        }

        if (mLeftDrawable != null) {
            setLeftDrawable(mLeftDrawable);
        }

        if (!TextUtils.isEmpty(mRightCharSequence)) {
            setRightText(mRightCharSequence);
        }

        if (!TextUtils.isEmpty(mLeftCharSequence)) {
            setLeftText(mLeftCharSequence);
        }
    }

    private void initListener() {
        mLeftText.setOnClickListener(this);
        mLeftButton.setOnClickListener(this);
        mRightButton.setOnClickListener(this);
        mRightText.setOnClickListener(this);
    }

    public void setLeftText(@NonNull CharSequence leftCharSequence) {
        if (mLeftText.getVisibility() != View.VISIBLE) {
            mLeftText.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(leftCharSequence)) {
            mLeftText.setText(leftCharSequence);
        }
    }

    public void setRightText(@NonNull CharSequence rightText) {
        if (mRightText.getVisibility() != View.VISIBLE) {
            mRightText.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(rightText)) {
            mRightText.setText(rightText);
        }
    }

    public void setTitle(@NonNull CharSequence title) {
        if (mTitle.getVisibility() != View.VISIBLE) {
            mTitle.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
            mTitleCharSequence = title;
        }
    }

    public void setTitle(int resId) {
        if (mTitle.getVisibility() != View.VISIBLE) {
            mTitle.setVisibility(View.VISIBLE);
        }

        if (resId > 0) {
            mTitle.setText(getResources().getText(resId));
            mTitleCharSequence = getResources().getText(resId);
        }
    }

    public void setRightDrawable(@NonNull Drawable rightImage) {
        if (mRightButton.getVisibility() != View.VISIBLE) {
            mRightButton.setVisibility(View.VISIBLE);
        }

        mRightButton.setImageDrawable(rightImage);
        mRightDrawable = rightImage;
    }

    public void setLeftDrawable(@NonNull Drawable leftDrawable) {
        if (mLeftButton.getVisibility() != View.VISIBLE) {
            mLeftButton.setVisibility(View.VISIBLE);
        }

        if (mLeftText.getVisibility() != View.GONE) {
            mLeftText.setVisibility(View.GONE);
        }

        mLeftButton.setImageDrawable(leftDrawable);
        mLeftDrawable = leftDrawable;
    }

    public void setLeftDrawableEnable(boolean isEnable) {
        mLeftButton.setEnabled(isEnable);
    }

    public void hiddenRightDrawable() {
        if (mRightButton.getVisibility() != View.GONE) {
            mRightButton.setVisibility(View.GONE);
        }
    }

    public void hiddenLeftText() {
        if (mLeftText.getVisibility() != View.GONE) {
            mLeftText.setVisibility(View.GONE);
        }
    }

    public void showRightDrawable() {
        if (mRightButton.getVisibility() != View.VISIBLE) {
            mRightButton.setVisibility(View.VISIBLE);
        }
    }

    public void setRightDrawableEnable(boolean isEnable) {
        mRightButton.setEnabled(isEnable);
    }

    public void hiddenRightText() {
        if (mRightText.getVisibility() != View.GONE) {
            mRightText.setVisibility(View.GONE);
        }
    }

    public void showRightText() {
        if (mRightText.getVisibility() != View.VISIBLE) {
            mRightText.setVisibility(View.VISIBLE);
        }
    }

    public void setRightTextEnable(boolean isEnable) {
        mRightText.setEnabled(isEnable);
    }

    @Override
    public void setBackgroundResource(int resId) {
        if (getChildAt(0) != null) {
            getChildAt(0).setBackgroundResource(resId);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_left_back_text:
                if (mLeftTextClickListener != null) {
                    mLeftTextClickListener.onClickListener();
                } else {
                    if (getContext() instanceof Activity) {
                        ((Activity) getContext()).finish();
                    }
                }
                break;
            case R.id.title_bar_left_image:
                if (mLiftImageClickListener != null) {
                    mLiftImageClickListener.onClickListener();
                }
                break;
            case R.id.title_bar_right_image:
                if (mRightImageClickListener != null) {
                    mRightImageClickListener.onClickListener();
                }
                break;
            case R.id.title_bar_right_text:
                if (mRightTextClickListener != null) {
                    mRightTextClickListener.onClickListener();
                }
        }
    }

    private TitleBarLeftImageClickListener mLiftImageClickListener;

    public void setLiftImageClickListener(TitleBarLeftImageClickListener listener) {
        this.mLiftImageClickListener = listener;
    }

    public interface TitleBarLeftImageClickListener {
        void onClickListener();
    }

    private TitleBarRightImageClickListener mRightImageClickListener;

    public void setRightImageClickListener(TitleBarRightImageClickListener listener) {
        this.mRightImageClickListener = listener;
    }

    public interface TitleBarRightImageClickListener {
        void onClickListener();
    }

    private TitleBarRightTextClickListener mRightTextClickListener;

    public void setRightTextClickListener(TitleBarRightTextClickListener listener) {
        this.mRightTextClickListener = listener;
    }

    public interface TitleBarRightTextClickListener {
        void onClickListener();
    }

    private TitleBarLeftTextClickListener mLeftTextClickListener;

    public void setLiftTextClickListener(TitleBarLeftTextClickListener listener) {
        this.mLeftTextClickListener = listener;
    }

    public interface TitleBarLeftTextClickListener {
        void onClickListener();
    }
}
