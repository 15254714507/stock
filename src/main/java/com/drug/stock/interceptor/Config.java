package com.drug.stock.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author lenovo
 */
@Configuration
public class Config implements WebMvcConfigurer {
    @Resource
    private  IdentityInterceptor identityInterceptor;
    @Resource
    private SuperAdminInterceptor superAdminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对于登录页面和登录操作不进行拦截
        String [] excludes=new String[]{"/","/Login.do"};
        registry.addInterceptor(identityInterceptor).addPathPatterns("/**").excludePathPatterns(excludes);
        //下面是超级管理员操作的拦截
        registry.addInterceptor(superAdminInterceptor).addPathPatterns("/gotoUserList.do").excludePathPatterns(excludes);
    }
}
