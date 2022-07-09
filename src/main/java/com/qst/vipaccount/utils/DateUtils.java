package com.qst.vipaccount.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date String2Date(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date timeStamp2Date(Long timeStamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String2Date(dateFormat.format(timeStamp));
    }

    public static String FileTimeStamp2Date(String seconds) {
        String format = "yyyy年MM月dd日 HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    public static String FileTimeStamp2Date(long TimeStamp) {
        String format = "yyyy年MM月dd日 HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(TimeStamp));
    }
}
