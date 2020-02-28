package com.drug.stock.controller;

import com.drug.stock.entity.domain.User;
import com.drug.stock.service.UserService;
import com.drug.stock.sumbit.UserForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author lenovo
 */
@Slf4j
@Controller
public class LoginController {
    private final static String SYSTEM_ERROR = "系统异常，请刷新后重新";
    private final static String ACCOUNT_ERROR = "账号错误，请重新输入";
    private final static String PASSWORD_ERROR = "密码错误，请重新输入";
    @Resource
    private UserService userService;

    /**
     * 进入登录页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/")
    public String init(Model model) {
        return "login";
    }

    /**
     *
     * @param model
     * @param userForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping(value = "/Login.do")
    public String login(Model model, @Valid UserForm userForm, BindingResult bindingResult, HttpSession session) {
        //表单验证信息是否符合要求
        if (bindingResult.hasErrors()) {
            model.addAttribute("systemError", SYSTEM_ERROR);
            return "login";
        }
        User user = null;
        try {
            user = userService.getUserByAccount(userForm.getUserAccount());
        } catch (Exception e) {
            log.error("登录获取user失败，account：{}", userForm.getUserAccount(), e);
            model.addAttribute("systemError", SYSTEM_ERROR);
            return  "login";
        }
        if (!validateUser(model, user, userForm)) {
            model.addAttribute("account",userForm.getUserAccount());
            model.addAttribute("password",userForm.getPassword());
            return "login";
        }
        session.setAttribute(session.getId(), user.getAccount());
        model.addAttribute("userName", user.getName());
        return "main/main";
    }

    /**
     * 验证提交的账号密码的是否正确
     *
     * @param model
     * @param user     数据库中查出的user信息
     * @param userForm 登录提交的表单
     * @return
     */
    private Boolean validateUser(Model model, User user, UserForm userForm) {
        if (user == null) {
            model.addAttribute("accountError", ACCOUNT_ERROR);
            return false;
        }
        if (!Objects.equals(user.getPassword(), userForm.getPassword())) {
            model.addAttribute("passwordError", PASSWORD_ERROR);
            return false;
        }
        return true;
    }
}
