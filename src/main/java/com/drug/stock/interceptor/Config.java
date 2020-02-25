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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对于静态资源和登录也不进行拦截
//        String [] excludes=new String[]{"/Login.html","/static/**"};
//        registry.addInterceptor(identityInterceptor).addPathPatterns("/**").excludePathPatterns(excludes);
    }
}
