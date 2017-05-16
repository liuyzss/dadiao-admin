package com.dadiao.wang.controller;

import com.dadiao.wang.dao.po.User;
import com.dadiao.wang.dao.po.UserExample;
import com.dadiao.wang.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Map<String,Object> getUserList(@RequestParam("draw") Long draw,@RequestParam("start") Integer start,@RequestParam("length") Integer length) {
        Map<String, Object> map = new HashMap<>();
        UserExample example = new UserExample();
        example.setLimit(length);
        example.setOffset(start);
        List<User> users = userMapper.selectByExample(example);
        Long count = userMapper.countByExample(example);
        map.put("data", users);
        map.put("draw",draw+1);
        map.put("recordsFiltered",count);
        map.put("recordsTotal",count);
        return map;
    }
}
