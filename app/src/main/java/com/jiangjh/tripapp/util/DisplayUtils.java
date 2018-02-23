package com.jiangjh.tripapp.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by JiaHao.Huang on 2018/2/22.
 */

public class DisplayUtils {


    /**
     * dp 转 px
     * @param value
     * @return
     */
    public static float convertDp2Px(int value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, AgentApplication.getInstance().getResources().getDisplayMetrics());
    }

    /**
     * dp 转 px
     * @param value
     * @return
     */
    public static float convertDp2Px(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, AgentApplication.getInstance().getResources().getDisplayMetrics());
    }

    /**
     * sp 转 px
     * @param value
     * @return
     */
    public static float convertSp2Px(int value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, AgentApplication.getInstance().getResources().getDisplayMetrics());
    }

    /**
     * sp 转 px
     * @param value
     * @return
     */
    public static float convertSp2Px(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, AgentApplication.getInstance().getResources().getDisplayMetrics());
    }

//    /**
//     * sp 转 px
//     * @param value
//     * @return
//     */
//    public static float convertSp2Px(int value) {
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, AgentApplication.getInstance().getResources().getDisplayMetrics());
//    }

    public static int getWidth() {
        WindowManager manager = (WindowManager) AgentApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getHeight() {
        WindowManager manager = (WindowManager) AgentApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getTotalHeightOfListView(ListView listView) {
        ListAdapter mAdapter = listView.getAdapter();
        if (mAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);
            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            //mView.measure(0, 0);
            totalHeight += mView.getMeasuredHeight();
        }
        return totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
    }
}
