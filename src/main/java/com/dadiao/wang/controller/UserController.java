package com.dadiao.wang.controller;

import com.dadiao.wang.dao.po.User;
import com.dadiao.wang.dao.po.UserExample;
import com.dadiao.wang.dao.po.UserRole;
import com.dadiao.wang.mapper.RoleMapper;
import com.dadiao.wang.mapper.UserMapper;
import com.dadiao.wang.mapper.UserRoleMapper;
import com.dadiao.wang.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    UserRoleMapper userRoleMapper;

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

    @RequestMapping(value = "/user/edit",method = RequestMethod.POST)
    @ResponseBody
    public Object getUserList(User user) {
        logger.info("------进入用户信息修改-------");
        Subject currentUser = SecurityUtils.getSubject();
        String username = (String) currentUser.getPrincipal();

        if(null != user.getId()){
            user.setUpdatedDate(new Date());
            user.setUpdatedBy(username);
            userMapper.updateByPrimaryKeySelective(user);
        }else{
            user.setCreatedBy(username);
            user.setCreatedDate(new Date());
            userMapper.insert(user);

            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(2);
        }
        return ResponseUtil.success();
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
