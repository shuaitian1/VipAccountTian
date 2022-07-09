package com.qst.vipaccount.utils;

import org.springframework.util.StringUtils;

import java.util.UUID;

public class CommonUtil {

    /**
     * 生成AccountId
     *
     * @return
     */
    public static String generateAccountId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成登录Token
     *
     * @return
     */
    public static String generateAccessToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * 校验用户名是否合法
     *
     * @param accountName
     * @return
     */
    public static boolean validateAccountName(String accountName) {
        if (StringUtils.hasText(accountName)) {
            if (accountName.matches("^[0-9a-zA-Z_]{8,20}$")) {
                return true;
            }
        }
        return false;
    }

}
