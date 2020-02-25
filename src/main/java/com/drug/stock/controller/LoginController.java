package com.drug.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lenovo
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/")
    public String login(Model model) {
        return "login";
    }
}
