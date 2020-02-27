package com.drug.stock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lenovo
 */
@Slf4j
@Controller
public class MainController {
    /**
     * 进入登录页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/home.do")
    public String init(Model model) {
        return "home";
    }

}
