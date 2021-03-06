package com.jiangjh.tripapp.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 *
 * @author Jinghao.Jiang
 * @date 2018/2/24
 */

public class HttpUtil {

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
