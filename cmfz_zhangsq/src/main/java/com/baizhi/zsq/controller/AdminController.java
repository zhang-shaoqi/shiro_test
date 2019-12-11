package com.baizhi.zsq.controller;


import com.baizhi.zsq.entity.Admin;
import com.baizhi.zsq.service.AdminService;
import com.baizhi.zsq.util.ImageCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

/**
 * 管理员模块 ：控制层
 */

@Controller //创建AdminController简单对象
@RequestMapping("/admin")   //访问后台路径
public class AdminController {


    @Resource   //给adminService做注入
    private AdminService adminService;

    /**
     * 管理员登录方法
     *
     * @param admin
     * @param session
     * @param enCode
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public HashMap<String,Object> login(Admin admin, HttpSession session, String enCode){
        HashMap<String, Object> map = adminService.login(admin, session, enCode);
        return map;
    }

    /**
     * 管理员登录验证码图片
     *
     * @param session
     * @param response
     */
    @RequestMapping("/getImageCode")
    public void getImageCode(HttpSession session, HttpServletResponse response){
        try {
            //获得随机字符
            String securityCode = ImageCodeUtil.getSecurityCode();
            session.setAttribute("securityCode",securityCode);
            //生成图片
            BufferedImage image = ImageCodeUtil.createImage(securityCode);
            //获取输出流
            ServletOutputStream outputStream = response.getOutputStream();
            //将生成的验证码图片以png(1.png)的格式写入到输出流中
            ImageIO.write(image, "png",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("AdminLogin");
        return "redirect:/login/login.jsp";
    }


}
