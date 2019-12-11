package com.baizhi.zsq.controller;


import com.baizhi.zsq.entity.Album;
import com.baizhi.zsq.entity.Article;
import com.baizhi.zsq.entity.Banner;
import com.baizhi.zsq.service.AlbumService;
import com.baizhi.zsq.service.ArticleService;
import com.baizhi.zsq.service.BannerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 *
 * 前台app接口
 */
@RestController
public class InterfaceController {

    /**
     *  BannerService：注入轮播图业务层
     */
    @Resource
    private BannerService bannerService;

    /**
     *  albumService:注入专辑业务层
     */
    @Resource
    private AlbumService albumService;

    /**
     *  articleService:注入文章业务层
     */
    @Resource
    private ArticleService articleService;


    /**
     * 一级页面接口
     *
     * @param uid        用户唯一标示	必填
     * @param type      请求数据类型（首页：all，闻：wen，思：si）	必填
     * @param sub_type  上师言教：ssyj，显密法要：xmfy	选填（当type为si的时候传入）
     * @return
     */
    @RequestMapping("/first_page")
    public HashMap<String,Object> firstPage(String uid, String type, String sub_type){
        //创建一个map集合
        HashMap<String, Object> map = new HashMap<>();

        if ("all".equals(type)){    //首页：all
            //获取状态是展示的轮播图    添加到map中
            List<Banner> banners = bannerService.selectBannerByStatus("yes");
            map.put("header",banners);
            //获取最新上传的6个专辑数据
            List<Album> albums = albumService.selectAlbumByPubDate(6);
            map.put("body",albums);

            //获取上师id
            List<Article> articles = articleService.selectArticleById(uid);
            map.put("trail",articles);

        }else if ("wen".equals(type)){  //闻：wen
            //获取所有专辑数据
            List<Album> albums = albumService.selectAlbumByPubDate(null);
            map.put("wen",albums);
        }else if ("si".equals(type)){   //思：si
            if ("ssyj".equals(sub_type)){   //上师言教：ssyj
                //根据id查询所有上师文章
                List<Article> articles = articleService.selectArticleById(uid);
                map.put("ssyj",articles);

            }else if ("xmfy".equals(sub_type)){ //显密法要：xmfy
                //查询所有文章
                List<Article> articles = articleService.selectArticleById(null);
                map.put("xmfy",articles);
            }
        }
        return map;
    }
}
