package com.baizhi.zsq.service;

import com.baizhi.zsq.entity.Album;
import com.baizhi.zsq.entity.Chapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface ChapterService {

    //根据分页获取音频
    HashMap<String,Object> selectChapterByPage(Integer page, Integer rows,String id);

    //添加音频方法
    String add(Chapter chapter);

    //修改音频方法
    String oper(Chapter chapter);

    //删除音频方法
    String del(Chapter chapter);

    //音频文件上传
    void chapterUpload(String id, MultipartFile file, HttpSession session);

    //音频文件下载和在线播放
    ResponseEntity<byte[]> chapterOperation(String operation, String src, HttpSession session);
}
