package com.dadiao.wang.controller;

import com.dadiao.wang.dao.po.User;
import com.dadiao.wang.dao.po.UserExample;
import com.dadiao.wang.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userView(Map<String, Object> model) {
        return "user";
    }

    @RequestMapping("/user/edit/{userId}")
    public String getUserList(@PathVariable("userId") Integer userId, Model model) {
        logger.info("------进入用户信息修改-------");
        User user = null;
        if (null != userId && null != (user = userMapper.selectByPrimaryKey(userId))) {
            model.addAttribute("user", user);
        }
        return "editSalesUser";
    }


    @RequestMapping(value = "/user/data", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserList(@RequestParam("draw") Long draw, @RequestParam("start") Integer start, @RequestParam("length") Integer length, @RequestParam("search[value]") String value) {
        Map<String, Object> map = new HashMap<>();
        UserExample example = new UserExample();
        example.setLimit(length);
        example.setOffset(start);
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameLike("%" + value + "%");
        List<User> users = userMapper.selectByExample(example);
        Long count = userMapper.countByExample(example);
        map.put("data", users);
        map.put("draw", draw + 1);
        map.put("recordsFiltered", count);
        map.put("recordsTotal", count);
        return map;
    }
}
