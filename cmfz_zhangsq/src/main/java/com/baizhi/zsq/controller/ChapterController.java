package com.baizhi.zsq.controller;

import com.baizhi.zsq.entity.Album;
import com.baizhi.zsq.entity.Chapter;
import com.baizhi.zsq.service.ChapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 音频控制层
 */

@Controller
@RequestMapping("/chapter")
public class ChapterController {


    @Resource
    private ChapterService chapterService;


    /**
     * 根据专辑id获取分页音频信息
     *
     * @param page
     * @param rows
     * @param id
     * @return
     */
    @RequestMapping("/selectChapterByPage")
    @ResponseBody
    public HashMap<String,Object> selectChapterByPage(Integer page,Integer rows,String id){
        HashMap<String, Object> map = chapterService.selectChapterByPage(page, rows,id);
        return map;
    }

    /**
     * 音频增删改操作
     *
     * @param chapter   音频信息
     * @param oper      操作音频增删改信息
     * @return  返回音频id为了实现后续操作
     */
    @RequestMapping("/compileChapter")
    @ResponseBody
    public String compileChapter(Chapter chapter, String oper){
        String id=null;
        if("add".equals(oper)){ //判断是否是添加方法
            id=chapterService.add(chapter);
        }else if ("edit".equals(oper)){//判断是否是修改方法
            id=chapterService.oper(chapter);
        }else if ("del".equals(oper)){//判断是否是删除方法
            id=chapterService.del(chapter);
        }
        return id;
    }

    /**
     * 音频文件上传
     *
     * @param id    文件上传地id
     * @param src   上传的文件
     * @param session   session作用域
     */
    @RequestMapping("/chapterUpload")
    public void chapterUpload(String id, MultipartFile src, HttpSession session){
        chapterService.chapterUpload(id,src,session);
    }

    /**
     * 音频下载 和 在线播放
     *
     * @param operation     音频操作  下载或播放
     * @param src       音频信息
     * @param session       session作用域
     */
    @RequestMapping("/chapterOperation")
    public ResponseEntity<byte[]> chapterOperation(String operation, String src, HttpSession session){
        System.out.println("operation======"+operation);
        System.out.println("src======"+src);
        ResponseEntity<byte[]> responseEntity = chapterService.chapterOperation(operation, src, session);
        return responseEntity;
    }




}
