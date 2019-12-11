package com.baizhi.zsq.service;

import com.baizhi.zsq.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface BannerService {


    //根据分页查询轮播图           当前页数：page        展示条数：rows
    HashMap<String, Object> selectBannerByPage(Integer page, Integer rows);

    //根据id修改状态
    void updateStatus(Banner banner);

    //添加轮播图
    String add(Banner banner);

    //修改轮播图
    String oper(Banner banner);

    //删除轮播图
    String del(Banner banner);

    //轮播图文件上传
    void bannerUpload(String id, MultipartFile picture, HttpSession session);

    //根据状态获取轮播图
    List<Banner> selectBannerByStatus(String status);
}
