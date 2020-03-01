package com.drug.stock.controller;

import com.alibaba.fastjson.JSON;
import com.drug.stock.entity.condition.UserCondition;
import com.drug.stock.entity.domain.User;
import com.drug.stock.service.UserService;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author lenovo
 */
@Slf4j
@Controller
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 前往用户信息管理页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoUserList.do")
    public String gotoUserList(Model model) {
        try {
            Long superAdminNum = userService.countUserBySuperAdmin(true);
            model.addAttribute("superAdminNum", superAdminNum);
            Long adminNum = userService.countUserBySuperAdmin(false);
            model.addAttribute("adminNum", adminNum);
        } catch (Exception e) {
            log.error("跳转到管理员列表页面时获得管理员的数量报错", e);
            return "error/404";
        }
        return "user/adminList";
    }

    @RequestMapping(value = "/gotoUserTable.do")
    public String getUserTable(Model model) {
        UserCondition userCondition = new UserCondition();
        try {
            PageInfo<User> page = userService.findUserPage(userCondition);
            model.addAttribute("page", page);
        } catch (Exception e) {
            log.error("获期用户信息列表出现错误", e);
            return "error/404";
        }
        return "user/table";
    }
}
