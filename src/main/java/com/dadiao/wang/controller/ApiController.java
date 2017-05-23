package com.dadiao.wang.controller;

import com.dadiao.wang.vo.BatchUserVo;
import com.dadiao.wang.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liuyang on 2017/5/2.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @RequestMapping("/addUser")
    public String addUser(Model model) {
        return "addUser";
    }

    @RequestMapping("/batchAddUser")
    public String batchAddUser(Model model) {
        return "batchAddUser";
    }

    @RequestMapping(path="/addUser",method = RequestMethod.POST)
    public String addUser(UserVo userVo) {
        return "addUser";
    }


    @RequestMapping(path = "/batchAddUser",method = RequestMethod.POST)
    public String batchAddUser(BatchUserVo batchUserVo) {
        return "batchAddUser";
    }
}
