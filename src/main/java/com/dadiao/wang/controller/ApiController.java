package com.dadiao.wang.controller;

import com.alibaba.fastjson.JSON;
import com.dadiao.wang.constant.InventoryLogEnum;
import com.dadiao.wang.constant.UrlConstant;
import com.dadiao.wang.dao.po.InventoryLog;
import com.dadiao.wang.mapper.InventoryLogMapper;
import com.dadiao.wang.service.AddUserService;
import com.dadiao.wang.util.BeanMapUtil;
import com.dadiao.wang.util.HttpClientService;
import com.dadiao.wang.util.HttpResult;
import com.dadiao.wang.util.ResponseUtil;
import com.dadiao.wang.vo.ApiVO;
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
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by liuyang on 2017/5/2.
 */
@Controller
@RequestMapping("/api")
public class ApiController {


    @Resource
    private HttpClientService httpClientService;

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
        HttpResult res = null;
        ApiVO apiVO = null;
        try {
            Map<String, String> params = BeanMapUtil.bean2MapStr(userVO);
            res = httpClientService.doPost(UrlConstant.ADD_USER_URL, params);
            apiVO = JSON.parseObject(res.getData(), ApiVO.class);
            if (apiVO.getCode().equals(0)) {
                Subject currentUser = SecurityUtils.getSubject();
                String username = (String) currentUser.getPrincipal();
                addUserService.addUserLog(username,userVO);
                return ResponseUtil.success();

            }
            return ResponseUtil.response("1001", apiVO, apiVO.getMsg());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseUtil.response("0001", null, "开户失败");
    }

    @RequestMapping(path = "/batchAddUser", method = RequestMethod.POST)
    @ResponseBody
    public Object batchAddUser(BatchUserVO batchUserVO) {
        HttpResult res = null;
        ApiVO apiVO = null;
        try {
            Map<String, String> params = BeanMapUtil.bean2MapStr(batchUserVO);
            res = httpClientService.doPost(UrlConstant.BATCH_ADD_USER_URL, params);
            apiVO = JSON.parseObject(res.getData(), ApiVO.class);
            if (apiVO.getCode().equals(0)) {
                InventoryLog inventoryLog = new InventoryLog();
                Subject currentUser = SecurityUtils.getSubject();
                String username = (String) currentUser.getPrincipal();
                inventoryLog.setUsername(username);
                inventoryLog.setAmount(1);
                inventoryLog.setOperateType((byte) InventoryLogEnum.ADD_USER.value);
                inventoryLog.setCreatedTime(new Date());
                inventoryLog.setCreatedBy(username);
                inventoryLog.setExtJson(JSON.toJSONString(batchUserVO));
                inventoryLogMapper.insert(inventoryLog);
                return ResponseUtil.success();

            }
            return ResponseUtil.response("1001", apiVO, apiVO.getMsg());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseUtil.response("0001", null, "开户失败");
    }
}
