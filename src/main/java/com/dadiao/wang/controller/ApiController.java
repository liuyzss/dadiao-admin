package com.dadiao.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuyang on 2017/5/2.
 */
@Controller
public class ApiController {
    @RequestMapping("/addUser")
    public String addUser(Model model) {
        return "addUser";
    }
    @RequestMapping("/batchAddUser")
    public String batchAddUser(Model model) {
        return "batchAddUser";
    }
}
