package com.baizhi.zsq.service;

import com.baizhi.zsq.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface UserService {

    //根据分页查询用户
    HashMap<String, Object> selectUserByPage(Integer page, Integer rows);

    //添加用户
    String add(User user);

    //修改用户
    String oper(User user);

    //删除用户
    String del(User user);

    //文件导出
    String userExport(HttpSession session);

    //文件上传
    void bannerUpload(String id, MultipartFile picImg, HttpSession session);

    //获取用户统数据
    HashMap<String, Object> userCount(Integer year);

    //获取用户分布
    HashMap<String, Object> userDistribution();
}
