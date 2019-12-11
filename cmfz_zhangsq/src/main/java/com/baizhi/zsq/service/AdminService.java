package com.baizhi.zsq.service;

import com.baizhi.zsq.entity.Admin;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface AdminService {

    //根据用户名查询用户
    HashMap<String,Object> login(Admin admin, HttpSession session,String enCode);


}
