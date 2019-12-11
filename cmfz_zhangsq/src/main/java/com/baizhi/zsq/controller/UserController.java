package com.baizhi.zsq.controller;

import com.alibaba.fastjson.JSON;
import com.baizhi.zsq.entity.User;
import com.baizhi.zsq.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     *根据分页查询用户
     *
     * @param page      当前页数
     * @param rows      每页显示条数
     * @return      返回map数据
     */
    @RequestMapping("/selectUserByPage")
    @ResponseBody
    public HashMap<String,Object> selectUserByPage(Integer page,Integer rows){
        HashMap<String,Object> map = userService.selectUserByPage(page,rows);
        return map;
    }

    @RequestMapping("/compileUser")
    @ResponseBody
    public String compileUser(User user,String oper,HttpSession session){
              String id=null;
        if("add".equals(oper)){ //判断是否是添加方法
            id=userService.add(user);
        }else if ("edit".equals(oper)){//判断是否是修改方法
            id=userService.oper(user);
        }else if ("del".equals(oper)){//判断是否是删除方法
            id=userService.del(user);
        }


        //用户注册统计实时更新
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-ed5b2995279f4d0a82d4a8d201004296");
        HashMap<String, Object> userCountMap = userService.userCount((Integer) session.getAttribute("year"));
        String userCountMaps = JSON.toJSONString(userCountMap);
        goEasy.publish("userCount",userCountMaps);


        //用户分布实时更新
        HashMap<String, Object> userDistributionMap = userService.userDistribution();
        String userDistributionMaps = JSON.toJSONString(userDistributionMap);
        goEasy.publish("userDistribution", userDistributionMaps);

        return id;
    }



    /**
     * 用户文件上传
     *
     * @param id
     * @param picImg
     * @param session
     */
    @RequestMapping("/userUpload")
    public void userUpload(String id, MultipartFile picImg, HttpSession session){
        userService.bannerUpload(id,picImg,session);
    }



    @RequestMapping("/userExport")
    @ResponseBody
    public String userExport( HttpSession session){
        String result = userService.userExport(session);
        return result;
    }


    @RequestMapping("/userCount")
    @ResponseBody
    public HashMap<String,Object> userCount(Integer year,HttpSession session){
        session.setAttribute("year",year);
        HashMap<String,Object> map = userService.userCount(year);
        return map;
    }

    @RequestMapping("/userDistribution")
    @ResponseBody
    public HashMap<String,Object> userDistribution(){
        HashMap<String,Object> map = userService.userDistribution();
        return map;
    }

}
