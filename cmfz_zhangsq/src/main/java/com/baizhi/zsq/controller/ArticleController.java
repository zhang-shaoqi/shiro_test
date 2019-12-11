package com.baizhi.zsq.controller;

import com.baizhi.zsq.entity.Article;
import com.baizhi.zsq.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;


    /**
     *根据分页查询文章
     *
     * @param page      当前页数
     * @param rows      每页显示条数
     * @return      返回map数据
     */
    @RequestMapping("/selectArticleByPage")
    @ResponseBody
    public HashMap<String,Object> selectUserByPage(Integer page,Integer rows){
        HashMap<String,Object> map = articleService.selectArticleByPage(page,rows);
        return map;
    }

    @RequestMapping("/compileArticle")
    @ResponseBody
    public String compileUser(Article article, String oper){
        System.out.println(article);
        String id=null;
        if("add".equals(oper)){ //判断是否是添加方法
            String a =articleService.add(article);
        }else if ("edit".equals(oper)){//判断是否是修改方法
            String b=articleService.oper(article);
        }else if ("del".equals(oper)){//判断是否是删除方法
            String c=articleService.del(article);
        }
        return id;
    }


    /**
     * 文章文件上传
     *
     * @param imgFile
     * @param request
     */
    @RequestMapping("/articleUpload")
    @ResponseBody
    public HashMap<String, Object> bannerUpload(MultipartFile imgFile, HttpServletRequest request){
        HashMap<String, Object> map = articleService.articleUpload(imgFile, request);
        System.out.println("map========="+map);
        return map;
    }

    /**
     * 获取文章图片空间
     *
     * @param request
     */
    @RequestMapping("/uploadInterspace")
    @ResponseBody
    public HashMap<String, Object> uploadInterspace( HttpServletRequest request){
        HashMap<String, Object> map = articleService.uploadInterspace(request);
        System.out.println("map========="+map);
        return map;
    }

}
