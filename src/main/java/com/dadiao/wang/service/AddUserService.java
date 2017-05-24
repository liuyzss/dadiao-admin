package com.dadiao.wang.service;

import com.alibaba.fastjson.JSON;
import com.dadiao.wang.constant.InventoryLogEnum;
import com.dadiao.wang.dao.po.InventoryLog;
import com.dadiao.wang.dao.po.User;
import com.dadiao.wang.dao.po.UserExample;
import com.dadiao.wang.mapper.InventoryLogMapper;
import com.dadiao.wang.mapper.UserMapper;
import com.dadiao.wang.vo.BatchUserVO;
import com.dadiao.wang.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by liuyang on 2017/5/24.
 */
@Service
public class AddUserService {

    private UserMapper userMapper;

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
    public int addUserLog(String username,UserVO userVO){
        User user = this.getUserByName(username);
        //userMapper.
        InventoryLog inventoryLog = new InventoryLog();
        inventoryLog.setUsername(username);
        inventoryLog.setAmount(1);
        inventoryLog.setOperateType((byte) InventoryLogEnum.ADD_USER.value);
        inventoryLog.setCreatedTime(new Date());
        inventoryLog.setCreatedBy(username);
        inventoryLog.setExtJson(JSON.toJSONString(userVO));
        inventoryLogMapper.insert(inventoryLog);
       return 0;
    }

    public int batchAddUserLog(String username,BatchUserVO batchUserVO){
        return 0;
    }
}
