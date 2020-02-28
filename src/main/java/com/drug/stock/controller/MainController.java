package com.drug.stock.controller;

import com.drug.stock.entity.domain.User;
import com.drug.stock.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author lenovo
 */
@Slf4j
@Controller()
public class MainController {

    @Resource
    private UserService userService;

    /**
     * 进入登录页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/home.do")
    public String init(Model model) {
        return "main/home";
    }

    @RequestMapping(value = "/gotoAdminInformation.do")
    public String gotoAdminInformation(Model model, HttpSession session) {
        //如果session中没有信息，拦截器就会拦截住
        String account = (String) session.getAttribute(session.getId());
        User user = null;
        try {
            user = userService.getUserByAccount(account);
        } catch (Exception e) {
            log.error("获取用户信息失败，账号 account：{}", account, e);
        }
        if (user == null) {
            return "error/404";
        }
        model.addAttribute("user", user);
        return "main/adminInfo";
    }

}
