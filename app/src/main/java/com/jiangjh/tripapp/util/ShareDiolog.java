package com.jiangjh.tripapp.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiangjh.tripapp.R;

/**
 * Created by JiaHao.Huang on 2018/2/22.
 */

public class ShareDiolog  extends Dialog {
    private static final int WECHAT = 1;
    private static final int TIMELINE = 2;
    private static final int QQ = 3;
    private static final int QQZONE = 4;
    private static final int WEIBO = 5;
    private static final int QRCODE = 6;

    private Button mCloseBtn;
    private LinearLayout mShareItemsLayout;
    private Context mContext;

    public ShareDiolog(Context context) {
        this(context, R.style.base_dialog_theme);
    }

    public ShareDiolog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;

    }


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setWindowAnimations(R.style.bottom_dialog_anim);

        setContentView(R.layout.share_dialog_layout);
        findViews();
        initialItems();
        setListener();
    }

    private void findViews() {
        mCloseBtn = (Button) findViewById(R.id.share_btn_cancel);
        mShareItemsLayout = (LinearLayout) findViewById(R.id.ll_share_items);
    }

    private void initialItems() {
        mShareItemsLayout.removeAllViews();
        createItem(WECHAT);
        createItem(TIMELINE);
        createItem(QQ);
        createItem(WEIBO);
        createItem(QRCODE);
    }

    private void createItem(int type) {
        LinearLayout itemLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.share_item_layout, null);
        ImageView itemIcon = (ImageView) itemLayout.findViewById(R.id.img_item_icon);
        TextView itemName = (TextView) itemLayout.findViewById(R.id.tv_item_name);
        switch (type) {
            case WECHAT:
                itemIcon.setImageResource(R.mipmap.icon_wechat_invitation);
                itemName.setText(R.string.str_wechat);
                break;
            case TIMELINE:
                itemIcon.setImageResource(R.mipmap.icon_timeline_invitation);
                itemName.setText(R.string.str_timeline);
                break;
            case QQ:
                itemIcon.setImageResource(R.mipmap.icon_qq_invitation);
                itemName.setText(R.string.str_qq);
                break;
            case QQZONE:
                itemIcon.setImageResource(R.mipmap.icon_qzone);
                itemName.setText(R.string.str_qzone);
                break;

            case WEIBO:
                itemIcon.setImageResource(R.mipmap.icon_weibo_invitation);
                itemName.setText(R.string.str_weibo);
                break;
            case QRCODE:
                itemIcon.setImageResource(R.mipmap.icon_qrcode_invitation);
                itemName.setText(R.string.str_qrcode);
                break;
            default:
                break;
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.rightMargin = 10;
        layoutParams.leftMargin = 10;
        itemLayout.setLayoutParams(layoutParams);
        itemLayout.setTag(type);
        mShareItemsLayout.addView(itemLayout);

    }

    private void setListener() {
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowing()) {
                    dismiss();
                }
            }
        });


    }

    @Override
    public void show() {
        super.show();

    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
//        lp.width = DisplayUtils.getWidth();
        lp.gravity = Gravity.BOTTOM;
        onWindowAttributesChanged(lp);
    }
}

