package com.baizhi.zsq.controller;

import com.baizhi.zsq.entity.Album;
import com.baizhi.zsq.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 专辑控制层
 */

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Resource
    private AlbumService albumService;

    /**
     * 根据页数获取专辑数据
     *
     * @param page  当前页数
     * @param rows  每页显示条数
     * @return      查询出专辑的数据
     */
    @RequestMapping("/selectAlbumByPage")
    @ResponseBody
    public HashMap<String,Object> selectAlbumByPage(Integer page,Integer rows){
        HashMap<String, Object> map = albumService.selectAlbumByPage(page, rows);
        return map;
    }



    @RequestMapping("/compileAlbum")
    @ResponseBody
    public String compileBanner(Album album, String oper){
        String id=null;
        if("add".equals(oper)){ //判断是否是添加方法
            id=albumService.add(album);
        }else if ("edit".equals(oper)){//判断是否是修改方法
            id=albumService.oper(album);
        }else if ("del".equals(oper)){//判断是否是删除方法
            id=albumService.del(album);
        }
        return id;
    }

    /**
     * 专辑文件上传
     *
     * @param id
     * @param coverImg
     * @param session
     */
    @RequestMapping("/bannerUpload")
    public void bannerUpload(String id, MultipartFile coverImg, HttpSession session){
        albumService.albumUpload(id,coverImg,session);
    }






    /**
     * 根据时间获取最新上传的专辑数据
     */
    @RequestMapping("/selectAlbumByPubDate")
    @ResponseBody
    public List<Album> selectAlbumByPubDate(Integer number){
        List<Album> albums= albumService.selectAlbumByPubDate(number);
        return albums;
    }





}
