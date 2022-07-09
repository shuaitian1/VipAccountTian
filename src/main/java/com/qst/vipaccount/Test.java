package com.qst.vipaccount;

import com.qst.vipaccount.utils.DateUtils;

public class Test {
    public static void main(String[] args) {
        String nowTime = DateUtils.FileTimeStamp2Date("1657205892006");

        System.out.println(nowTime.substring(5,11).equalsIgnoreCase("07月07日"));
    }
}
