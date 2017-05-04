package com.dadiao.wang.mapper;

import com.dadiao.wang.dao.po.Salesman;
import com.dadiao.wang.dao.po.SalesmanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SalesmanMapper {
    long countByExample(SalesmanExample example);

    int deleteByExample(SalesmanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Salesman record);

    int insertSelective(Salesman record);

    List<Salesman> selectByExample(SalesmanExample example);

    Salesman selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Salesman record, @Param("example") SalesmanExample example);

    int updateByExample(@Param("record") Salesman record, @Param("example") SalesmanExample example);

    int updateByPrimaryKeySelective(Salesman record);

    int updateByPrimaryKey(Salesman record);
}