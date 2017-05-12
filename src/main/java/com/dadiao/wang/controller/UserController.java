package com.dadiao.wang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by liuyang on 2017/5/12.
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/user")
    public String getUserList(Map<String, Object> model) {
        //model.put("userList", userDao.getList());
        return "user";
    }

    @RequestMapping("/user/edit/{userid}")
    public String getUserList(@PathVariable int userid) {
        logger.info("------进入用户信息修改-------");
        return "user_edit";
    }
}
