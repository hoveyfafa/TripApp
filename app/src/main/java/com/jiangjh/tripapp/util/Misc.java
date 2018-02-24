package com.jiangjh.tripapp.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiangjh.tripapp.R;

/**
 *
 * @author Jinghao.Jiang
 * @date 2018/2/24
 */

public class Misc {

    public static void display(Context context, String url, ImageView imageView) {
        try {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundResource(R.color.c_E9);

                Glide.with(context)
                        .load(url)
                        .into(imageView);

        } catch (java.lang.IllegalArgumentException ignored) {
        }
    }
}
