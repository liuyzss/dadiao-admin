package com.dadiao.wang.controller;

import com.dadiao.wang.dao.po.User;
import com.dadiao.wang.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by liuyang on 2017/5/2.
 */
@Controller
public class MainController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "liuyang");
        return "index";
    }

    @RequestMapping("/indexTest")
    public String indexTest(Model model) {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
        model.addAttribute("name", "liuyang");
        return "index";
    }
}
