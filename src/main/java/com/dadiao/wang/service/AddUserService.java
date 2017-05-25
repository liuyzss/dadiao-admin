package com.dadiao.wang.service;

import com.alibaba.fastjson.JSON;
import com.dadiao.wang.common.NoInventoryException;
import com.dadiao.wang.constant.InventoryLogEnum;
import com.dadiao.wang.constant.UrlConstant;
import com.dadiao.wang.dao.po.InventoryLog;
import com.dadiao.wang.dao.po.User;
import com.dadiao.wang.dao.po.UserExample;
import com.dadiao.wang.mapper.InventoryLogMapper;
import com.dadiao.wang.mapper.UserMapper;
import com.dadiao.wang.util.BeanMapUtil;
import com.dadiao.wang.util.HttpClientService;
import com.dadiao.wang.util.HttpResult;
import com.dadiao.wang.vo.ApiVO;
import com.dadiao.wang.vo.BatchUserVO;
import com.dadiao.wang.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyang on 2017/5/24.
 */
@Service
public class AddUserService {

    @Resource
    private HttpClientService httpClientService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private InventoryLogMapper inventoryLogMapper;


    public User getUserByName(String username) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        User user = users.get(0);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
    public void addUserLog(String username, UserVO userVO) throws Exception {
        HttpResult res = null;
        ApiVO apiVO = null;
        User user = this.getUserByName(username);
        if (user.getInventory() - 1 >= 0) {
            user.setInventory(user.getInventory() - 1);
        } else {
            throw new NoInventoryException(user.getInventory());
        }
        Map<String, String> params = BeanMapUtil.bean2MapStr(userVO);
        res = httpClientService.doPost(UrlConstant.ADD_USER_URL, params);
        apiVO = JSON.parseObject(res.getData(), ApiVO.class);
        if (apiVO.getCode().equals(0)) {
            userMapper.updateByPrimaryKeySelective(user);
            InventoryLog inventoryLog = new InventoryLog();
            inventoryLog.setUsername(username);
            inventoryLog.setAmount(1);
            inventoryLog.setOperateType((byte) InventoryLogEnum.ADD_USER.value);
            inventoryLog.setCreatedTime(new Date());
            inventoryLog.setCreatedBy(username);
            inventoryLog.setExtJson(JSON.toJSONString(userVO));
            inventoryLogMapper.insert(inventoryLog);

        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
    public void batchAddUserLog(String username, BatchUserVO batchUserVO) throws Exception {
        HttpResult res = null;
        ApiVO apiVO = null;
        User user = this.getUserByName(username);
        if (user.getInventory() - batchUserVO.getCount() >= 0) {
            user.setInventory(user.getInventory() - batchUserVO.getCount());
        } else {
            throw new NoInventoryException(user.getInventory());
        }
        Map<String, String> params = BeanMapUtil.bean2MapStr(batchUserVO);
        res = httpClientService.doPost(UrlConstant.BATCH_ADD_USER_URL, params);
        apiVO = JSON.parseObject(res.getData(), ApiVO.class);
        if (apiVO.getCode().equals(0)) {
            userMapper.updateByPrimaryKeySelective(user);
            InventoryLog inventoryLog = new InventoryLog();
            inventoryLog.setUsername(username);
            inventoryLog.setAmount(1);
            inventoryLog.setOperateType((byte) InventoryLogEnum.ADD_USER.value);
            inventoryLog.setCreatedTime(new Date());
            inventoryLog.setCreatedBy(username);
            inventoryLog.setExtJson(JSON.toJSONString(batchUserVO));
            inventoryLogMapper.insert(inventoryLog);
        }
    }
}
