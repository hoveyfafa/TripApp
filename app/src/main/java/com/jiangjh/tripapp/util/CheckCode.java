package com.jiangjh.tripapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JiaHao.Huang on 2018/2/26.
 */

public class CheckCode {

    /**
     * 验证电话号码
     *
     * @param phone 电话号码
     * @return
     */
    public static Boolean checkPhone(String phone) {
        String check = "[1,7]\\d{10}";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(phone);
        return matcher.matches();
    }
}
