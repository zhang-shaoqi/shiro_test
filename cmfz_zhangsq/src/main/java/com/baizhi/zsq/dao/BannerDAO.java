package com.baizhi.zsq.dao;

import com.baizhi.zsq.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDAO {

    //添加轮播图
    void insertBanner(Banner banner);

    //获取轮播图的总数
    Integer countBanner();

    //根据分页查询轮播图
    List<Banner> selectBannerByPage(@Param("page")Integer page,@Param("rows")Integer rows);

    //根据id修改状态
    void updateStatus(Banner banner);

    //修改轮播图信息
    void updateBanner(Banner banner);

    //删除轮播图方法
    void delectBanner(Banner banner);

    //根据状态查询轮播图
    List<Banner> selectBannerByStatus(String status);
}
