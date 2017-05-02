package com.dadiao.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuyang on 2017/5/2.
 */
@Controller
public class MainController {
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "liuyang");
        return "index";
    }
}
