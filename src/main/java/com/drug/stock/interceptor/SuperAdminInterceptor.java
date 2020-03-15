package com.drug.stock.interceptor;

import com.drug.stock.entity.domain.User;
import com.drug.stock.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lenovo
 */
@Component("superAdminInterceptor")
public class SuperAdminInterceptor implements HandlerInterceptor {
    @Resource
    UserService userService;
    /**
     * 进入controller层之前的请求
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        String account = (String) httpSession.getServletContext().getAttribute(httpSession.getId());
        User user = userService.getUserByAccount(account);
        if(!user.getSuperAdmin()){
            //跳转到登录页面
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

}
