package com.dadiao.wang.controller;

import com.dadiao.wang.common.NoInventoryException;
import com.dadiao.wang.constant.ResponseEnum;
import com.dadiao.wang.service.AddUserService;
import com.dadiao.wang.util.ResponseUtil;
import com.dadiao.wang.vo.BatchUserVO;
import com.dadiao.wang.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by liuyang on 2017/5/2.
 */
@Controller
@RequestMapping("/api")
public class ApiController {


    @Resource
    private AddUserService addUserService;

    @RequestMapping("/addUser")
    public String addUser(Model model) {
        return "addUser";
    }

    @RequestMapping("/batchAddUser")
    public String batchAddUser(Model model) {
        return "batchAddUser";
    }

    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public Object addUser(UserVO userVO) {
        Subject currentUser = SecurityUtils.getSubject();
        String username = (String) currentUser.getPrincipal();
        try {
            addUserService.addUserLog(username, userVO);
        } catch (NoInventoryException e) {
            return ResponseUtil.response(ResponseEnum.NO_INVENTROY.value, null, ResponseEnum.NO_INVENTROY.desc);
        } catch (Exception e) {
            return ResponseUtil.response(ResponseEnum.API_ERROR.value, null, e.getMessage());
        }
        return ResponseUtil.success();
    }

    @RequestMapping(path = "/batchAddUser", method = RequestMethod.POST)
    @ResponseBody
    public Object batchAddUser(BatchUserVO batchUserVO) {
        Subject currentUser = SecurityUtils.getSubject();
        String username = (String) currentUser.getPrincipal();
        try {
            addUserService.batchAddUserLog(username, batchUserVO);
        } catch (NoInventoryException e) {
            return ResponseUtil.response(ResponseEnum.NO_INVENTROY.value, null, ResponseEnum.NO_INVENTROY.desc);
        } catch (Exception e) {
            return ResponseUtil.response(ResponseEnum.API_ERROR.value, null, e.getMessage());
        }
        return ResponseUtil.success();


    }
}
