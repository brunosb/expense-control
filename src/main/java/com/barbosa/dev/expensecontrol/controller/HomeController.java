package com.barbosa.dev.expensecontrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping("/login")
    public String login(ModelMap modelMap, HttpServletRequest request) {
        return "/pages/login";
    }

    @RequestMapping({"/home", "/"})
    public String home(ModelMap modelMap) {
        return "/pages/home";
    }
}
