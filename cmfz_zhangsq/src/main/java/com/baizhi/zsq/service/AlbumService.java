package com.baizhi.zsq.service;

import com.baizhi.zsq.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface AlbumService {

    //根据分页获取专辑
    HashMap<String,Object> selectAlbumByPage(Integer page, Integer rows);

    //添加专辑方法
    String add(Album album);

    //修改专辑方法
    String oper(Album album);

    //删除专辑方法
    String del(Album album);

    //专辑文件上传
    void albumUpload(String id, MultipartFile coverImg, HttpSession session);

    //根据时间获取最新上传的专辑数据
    List<Album> selectAlbumByPubDate(Integer number);
}
