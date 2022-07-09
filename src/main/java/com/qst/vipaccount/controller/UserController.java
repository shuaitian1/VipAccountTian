package com.qst.vipaccount.controller;

import com.qst.vipaccount.entity.bo.User;
import com.qst.vipaccount.entity.dto.AddUserRequestParams;
import com.qst.vipaccount.entity.dto.LoginRequestParams;
import com.qst.vipaccount.entity.vo.BaseResponseResult;
import com.qst.vipaccount.entity.vo.ResponseResultCode;
import com.qst.vipaccount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.util.StringUtils.isEmpty;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public BaseResponseResult login(@RequestBody LoginRequestParams loginRequestParams) {
        BaseResponseResult baseResponseResult = new BaseResponseResult();
        //用户信息
        User user = userService.findUserByUserNameAndPassword(loginRequestParams.getUserName(), loginRequestParams.getPassword());
        //账号不存在、密码错误
        if (user != null && user.getUserLever() == 0) {
            user.setToken(userService.createAndInsertToken(user.getUserId()));
            baseResponseResult.setData(user);
            baseResponseResult.setResultCode(ResponseResultCode.SUCCESS);
        } else {
            baseResponseResult.setResultCode(ResponseResultCode.ACCOUNT_OR_PASSWORD_ERROR);
        }
        return baseResponseResult;
    }

    @PostMapping("/logout")
    public BaseResponseResult logout(HttpServletRequest request) {
        BaseResponseResult baseResponseResult = new BaseResponseResult();
        //从request中取出token
        String token = request.getHeader("token");
        userService.logout(token);
        baseResponseResult.setError_message("退出成功！");
        return baseResponseResult;
    }

    @PostMapping("addUser")
    public BaseResponseResult addUser(@RequestBody AddUserRequestParams addUserRequestParams) {
        BaseResponseResult baseResponseResult = new BaseResponseResult();
        try {
            User user = userService.findUserByUserName(addUserRequestParams.userName);
            if (addUserRequestParams.getUserName() != null) {
                if (user == null) {
                    if (userService.addUser(addUserRequestParams) != 0) {
                        baseResponseResult.setResultCode(ResponseResultCode.SUCCESS);
                    } else {
                        baseResponseResult.setResultCode(ResponseResultCode.SYSTEM_PARAM_ERROR);
                    }
                } else {
                    baseResponseResult.setResultCode(ResponseResultCode.ACCOUNT_EXIST);
                }
            } else {
                baseResponseResult.setResultCode(ResponseResultCode.SYSTEM_PARAM_ERROR);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            baseResponseResult.setResultCode(ResponseResultCode.SYSTEM_ERROR);
        }
        return baseResponseResult;
    }

    @PostMapping("/findAllUser")
    public BaseResponseResult findAllUser(HttpServletRequest request) {
        BaseResponseResult baseResponseResult = new BaseResponseResult();
        User user = (User) request.getAttribute("token");
        try {
            if (user.getUserLever() == 0) {
                baseResponseResult.setData(userService.findAllUser());
                baseResponseResult.setResultCode(ResponseResultCode.SUCCESS);
            } else {
                baseResponseResult.setResultCode(ResponseResultCode.OPERATION_NO_POWER);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            baseResponseResult.setResultCode(ResponseResultCode.SYSTEM_ERROR);
        }

        return baseResponseResult;
    }

    @PostMapping("updateUserInfoByUserId")
    public BaseResponseResult updateUserInfoByUserId(@RequestBody AddUserRequestParams addUserRequestParams, HttpServletRequest request) {
        BaseResponseResult baseResponseResult = new BaseResponseResult();
        User user = (User) request.getAttribute("token");
        try {
            if (user.getUserLever() == 0) {
                if (!isEmpty(addUserRequestParams)) {
                    if (userService.updateUserInfoByUserId(addUserRequestParams) != 0) {
                        baseResponseResult.setResultCode(ResponseResultCode.SUCCESS);
                    } else {
                        baseResponseResult.setResultCode(ResponseResultCode.SYSTEM_ERROR);
                    }
                } else {
                    baseResponseResult.setResultCode(ResponseResultCode.SYSTEM_PARAM_ERROR);
                }
            } else {
                baseResponseResult.setResultCode(ResponseResultCode.OPERATION_NO_POWER);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            baseResponseResult.setResultCode(ResponseResultCode.SYSTEM_ERROR);
        }
        return baseResponseResult;
    }
}
