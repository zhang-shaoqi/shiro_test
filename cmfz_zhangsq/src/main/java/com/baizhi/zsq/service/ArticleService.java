package com.baizhi.zsq.service;

import com.baizhi.zsq.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface ArticleService {

    //根据分页查询文章
    HashMap<String, Object> selectArticleByPage(Integer page, Integer rows);

    //添加文章
    String add(Article article);

    //修改文章
    String oper(Article article);

    //删除文章
    String del(Article article);

    //文章图片上传
    HashMap<String,Object> articleUpload(MultipartFile imgFile, HttpServletRequest request);

    //获取文件空间
    HashMap<String, Object> uploadInterspace(HttpServletRequest request);

    //根据id查询
    List<Article> selectArticleById(String id);
}
