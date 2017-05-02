package com.dadiao.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liuyang on 2017/5/2.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
