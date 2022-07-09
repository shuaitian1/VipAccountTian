package com.qst.vipaccount.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 */
@Configuration
public class AuthConfig extends WebMvcConfigurationSupport {
    //注入自定义的 拦截器
    @Autowired
    AuthInterceptor authInterceptor;

    /**
     * 添加拦截器配置
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //添加自定义拦截器
        registry.addInterceptor(authInterceptor).addPathPatterns("/**") //指定拦截的url地址
                // 不拦截登录
                .excludePathPatterns("/login")
                // 不拦截错误页面
                .excludePathPatterns("/error");
    }
}
