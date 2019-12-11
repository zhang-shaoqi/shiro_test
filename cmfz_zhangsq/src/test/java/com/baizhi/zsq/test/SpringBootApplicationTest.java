package com.baizhi.zsq.test;


import com.alibaba.fastjson.JSON;
import com.baizhi.zsq.Application;
import com.baizhi.zsq.dao.AdminDAO;
import com.baizhi.zsq.dao.AlbumDAO;
import com.baizhi.zsq.dao.BannerDAO;
import com.baizhi.zsq.dao.ChapterDAO;
import com.baizhi.zsq.entity.Admin;
import com.baizhi.zsq.entity.Album;
import com.baizhi.zsq.entity.Banner;
import com.baizhi.zsq.entity.Chapter;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
public class SpringBootApplicationTest {

    @Resource
    private AdminDAO adminDAO;

    @Resource
    private BannerDAO bannerDAO;

    @Resource
    private AlbumDAO albumDAO;

    @Resource
    private ChapterDAO chapterDAO;


    @Resource
    StringRedisTemplate stringRedisTemplate;



    //管理员测试
    @Test
    public void test1(){
        //查询管理员
        Admin admin = new Admin("1", "admin", "admin");
        Admin adminByName = adminDAO.getAdminByName(admin);
        System.out.println(adminByName);
    }

    //轮播图测试
    @Test
    public void test2(){
       /*
        添加轮播图
        Banner banner = new Banner("1","1.jpg","哈哈","yse",new Date());
        bannerDAO.insertBanner(banner);
        */

       //查询轮播图
        List<Banner> bannerList = bannerDAO.selectBannerByPage(1,2);
        for (Banner banner : bannerList) {
            System.out.println(banner);
        }


    }

    //专辑测试
    @Test
    public void test3(){

        Integer integer = albumDAO.countAlbum();
        System.out.println(integer);
        List<Album> albumList = albumDAO.selectAlbumByPage(0, 3);
        for (Album album : albumList) {
            System.out.println(album);
        }
    }


    //音频测试
    @Test
    public void test4(){

        Integer integer = chapterDAO.countChapter("1");
        System.out.println(integer);
        List<Chapter> chapters = chapterDAO.selectChapter(0, 2,"4");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }



    @Test
    public void text5(){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second);

    }

    @Test
    public void text6(){

        //用户注册实时更新
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-ed5b2995279f4d0a82d4a8d201004296");

        goEasy.publish("userDistribution","hhhhhhhhhhhhhhhh");
    }



    @Test
    public void text7(){

        SetOperations<String, String> stringStringSetOperations = stringRedisTemplate.opsForSet();
        stringStringSetOperations.add("hhhh","zhangs");

    }





}
