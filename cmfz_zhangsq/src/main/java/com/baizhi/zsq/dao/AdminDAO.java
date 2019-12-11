package com.baizhi.zsq.dao;

import com.baizhi.zsq.entity.Admin;

public interface AdminDAO {

    //根据用户名查询用户
    Admin getAdminByName(Admin admin);

}
