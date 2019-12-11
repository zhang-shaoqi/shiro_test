package com.baizhi.zsq.serviceImpl;

import com.baizhi.zsq.dao.AdminDAO;
import com.baizhi.zsq.entity.Admin;
import com.baizhi.zsq.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


@Service    //创建service简单对象
@Transactional
public class AdminServiceImpl implements AdminService {

    @Resource   //给AdminDAO做注入
    private AdminDAO adminDAO;


    //根据用户名查询用户
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String,Object> login(Admin admin, HttpSession session, String enCode) {

        HashMap<String, Object> map = new HashMap<>();

        //判断验证码是否正确
        if (enCode.equals(session.getAttribute("securityCode"))){
            //根据用户名查询用户是否存在
            Admin adminByName = adminDAO.getAdminByName(admin);
            //判断用户是否存在
            if(adminByName!=null){
                //判断用户密码是否存在
                if(adminByName.getPassword().equals(admin.getPassword())){
                    //把用户存到session中
                    session.setAttribute("AdminLogin",adminByName);
                }else {
                    map.put("password","NO");
                }
            }else {
                map.put("username","NO");
            }
        }else {
            map.put("code","NO");
        }
        return map;
    }
}
