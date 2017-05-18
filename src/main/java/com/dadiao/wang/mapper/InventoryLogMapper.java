package com.dadiao.wang.mapper;

import com.dadiao.wang.dao.po.InventoryLog;
import com.dadiao.wang.dao.po.InventoryLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InventoryLogMapper {
    long countByExample(InventoryLogExample example);

    int deleteByExample(InventoryLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InventoryLog record);

    int insertSelective(InventoryLog record);

    List<InventoryLog> selectByExample(InventoryLogExample example);

    InventoryLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InventoryLog record, @Param("example") InventoryLogExample example);

    int updateByExample(@Param("record") InventoryLog record, @Param("example") InventoryLogExample example);

    int updateByPrimaryKeySelective(InventoryLog record);

    int updateByPrimaryKey(InventoryLog record);
}