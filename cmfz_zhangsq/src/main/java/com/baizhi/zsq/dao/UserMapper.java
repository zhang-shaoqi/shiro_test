package com.baizhi.zsq.dao;

import com.baizhi.zsq.entity.User;
import com.baizhi.zsq.entity.UserDistribution;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    //根据年份、月份、性别    查询人数
    Integer userCount(@Param("month") Integer month,@Param("year") Integer year,@Param("sex") String sex);

    //根据性别查询用户地区和地区人数
    List<UserDistribution> userDistribution(String sex);

}