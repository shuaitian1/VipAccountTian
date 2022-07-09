package com.qst.vipaccount.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qst.vipaccount.entity.bo.User;
import com.qst.vipaccount.entity.vo.BaseResponseResult;
import com.qst.vipaccount.entity.vo.ResponseResultCode;
import com.qst.vipaccount.service.UserService;
import com.qst.vipaccount.utils.BodyReaderHttpServletRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        //读取request内容后 重新写入
        String token = request.getHeader("token");

        //如果token为空
        if (StringUtils.isBlank(token)) {
            setReturn(response, ResponseResultCode.TOKEN_INVALID_ERROR);
            return false;
        }
        //1. 根据token，查询用户信息
        User user = userService.findUserByToken(token);
        //2. 若用户不存在,
        if (user == null) {
            setReturn(response, ResponseResultCode.TOKEN_INVALID_ERROR);
            return false;
        }
        request.setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    //返回错误信息
    private static void setReturn(HttpServletResponse response, ResponseResultCode status) throws IOException {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BaseResponseResult baseResponseResult = new BaseResponseResult();
        baseResponseResult.setResultCode(status);
        String json = baseResponseResult.toString();
        response.getWriter().print(new Gson().toJson(baseResponseResult));
    }

}