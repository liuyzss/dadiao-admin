package com.dadiao.wang.controller;

import com.dadiao.wang.dao.po.User;
import com.dadiao.wang.dao.po.UserExample;
import com.dadiao.wang.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyang on 2017/5/12.
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    UserMapper userMapper;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String userView(Map<String, Object> model) {
        return "user";
    }

    @RequestMapping("/user/edit/{userid}")
    public String getUserList(@PathVariable int userid) {
        logger.info("------进入用户信息修改-------");
        return "user_edit";
    }


    @RequestMapping(value = "/user/data",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserList() {
        Map<String, Object> model = new HashMap<>();
        UserExample example = new UserExample();
        example.setLimit(2);
        example.setOffset(0);
        List<User> users = userMapper.selectByExample(example);
        model.put("data", users);
        return model;
    }
}
