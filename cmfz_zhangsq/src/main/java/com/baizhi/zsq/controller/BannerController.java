package com.baizhi.zsq.controller;

import com.baizhi.zsq.entity.Banner;
import com.baizhi.zsq.service.BannerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 轮播图模块：控制层
 */

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Resource//给bannerService做注入
    private BannerService bannerService;

    /**
     * 编辑轮播图
     *
     * @param banner     接收banner信息
     * @param oper      获取增删改信息
     * @return          编辑轮播图的id
     */
    @RequestMapping("/compileBanner")
    public String compileBanner(Banner banner,String oper){
        String id=null;
        if("add".equals(oper)){ //判断是否是添加方法
            id=bannerService.add(banner);
            System.out.println("==================="+banner.getId());
        }else if ("edit".equals(oper)){//判断是否是修改方法
            id=bannerService.oper(banner);
        }else if ("del".equals(oper)){//判断是否是删除方法
            id=bannerService.del(banner);
        }
        return id;
    }

    /**
     * 根据分页获取
     *
     * @param page      当前页数
     * @param rows      显示条数
     * @return          banner集合
     */
    @RequestMapping("/selectBannerByPage")  //当前页数：page     显示条数：rows
    public HashMap<String, Object> selectBannerByPage(Integer page,Integer rows){
        HashMap<String, Object> map = bannerService.selectBannerByPage(page, rows);
        return map;
    }


    /**
     * 根据id修改状态
     *
     * @param banner    获取前台id和状态
     */
    @RequestMapping("/updateStatus")
    public void updateStatus(Banner banner){
        bannerService.updateStatus(banner);
    }

    /**
     * 轮播图文件上传
     *
     * @param id
     * @param srcImg
     * @param session
     */
    @RequestMapping("/bannerUpload")
    public void bannerUpload(String id, MultipartFile srcImg, HttpSession session){
        bannerService.bannerUpload(id,srcImg,session);
    }



}
