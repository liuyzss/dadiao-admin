package com.dadiao.wang.controller;

import com.dadiao.wang.dao.po.Role;
import com.dadiao.wang.dao.po.User;
import com.dadiao.wang.mapper.RoleMapper;
import com.dadiao.wang.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyang on 2017/5/2.
 */
@Controller
public class MainController {

    @Resource
    RoleMapper roleMapper;

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


    @RequestMapping("/dict/role")
    @ResponseBody
    public Object getRoleDict() {
        List<Role> roles = roleMapper.selectByExample(null);
        List<Object> res = new ArrayList<>();
        for (Role role : roles) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", role.getId());
            map.put("text", role.getRolename());
            res.add(map);

        }
        return res;
    }
}
