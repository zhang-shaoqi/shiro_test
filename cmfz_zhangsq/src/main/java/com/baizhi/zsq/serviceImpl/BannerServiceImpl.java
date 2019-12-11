package com.baizhi.zsq.serviceImpl;


import com.baizhi.zsq.dao.BannerDAO;
import com.baizhi.zsq.entity.Banner;
import com.baizhi.zsq.service.BannerService;
import com.baizhi.zsq.util.UUIDUtil;
import com.baizhi.zsq.util.UploadPictureUtil;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Resource//给bannerDAO做注入
    private BannerDAO bannerDAO;

    /**
     * 根据分页获取
     *
     * @param page      当前页数
     * @param rows      显示条数
     * @return          HashMap<String, Object>
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> selectBannerByPage(Integer page, Integer rows) {

        HashMap<String,Object> map = new ManagedMap<>();
        //总条数
        Integer records = bannerDAO.countBanner();
        map.put("records",records);
        //总页数
        Integer total = records%rows==0?records/rows:records/rows+1;
        map.put("total",total);
        //当前页
        map.put("page",page);
        //获取每页展示的数据
        List<Banner> banners = bannerDAO.selectBannerByPage((page - 1) * rows, rows);
        map.put("rows",banners);
        return map;


    }

    /**
     * 根据id修改状态
     *
     * @param banner    获取id
     */
    @Override
    public void updateStatus(Banner banner) {
        bannerDAO.updateStatus(banner);
    }

    /**
     * 轮播图添加方法
     *
     * @param banner    获取轮播图添加信息
     * @return  轮播图id
     */
    @Override
    public String add(Banner banner) {
        banner.setId(UUIDUtil.getUUID());
        banner.setUploadTime(new Date());
        banner.setStatus("yes");
        bannerDAO.insertBanner(banner);
        return banner.getId();
    }

    /**
     * 修改轮播图
     *
     * @param banner    修改轮播图的信息
     * @return  修改轮播图的id
     */
    @Override
    public String oper(Banner banner) {
        bannerDAO.updateBanner(banner);
        return banner.getId();
    }

    /**
     * 删除轮播图方法
     *
     * @param banner    删除轮播图的信息
     * @return  删除轮播图的id
     */
    @Override
    public String del(Banner banner) {
        bannerDAO.delectBanner(banner);
        return banner.getId();
    }

    /**
     * 轮播图文件上传
     *
     * @param id
     * @param srcImg
     * @param session
     */
    @Override
    public void bannerUpload(String id, MultipartFile srcImg, HttpSession session) {
        //调用上传工具类
        String name = UploadPictureUtil.upload(srcImg, session, "/banner/img");
        Banner banner = new Banner();
        banner.setSrcImg(name);
        banner.setId(id);
        bannerDAO.updateBanner(banner);
    }


    /**
     * 根据状态查询轮播图
     *
     * @return     轮播图所有展示的数据
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Banner> selectBannerByStatus(String status) {
        List<Banner> banners = bannerDAO.selectBannerByStatus(status);
        return banners;
    }


}
