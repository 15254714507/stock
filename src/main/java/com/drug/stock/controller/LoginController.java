package com.drug.stock.controller;

import com.drug.stock.sumbit.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author lenovo
 */
@Controller
public class LoginController {
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
     * 进行登录验证
     */
    @PostMapping(value = "/Login.do")
    public String login(Model model, @Valid UserForm userForm, BindingResult bindingResult) {
        //表单验证信息是否符合要求
        if (bindingResult.hasErrors()) {
            return "login";
        }
        return "main";
    }
}
