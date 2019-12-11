package com.baizhi.zsq.serviceImpl;

import com.baizhi.zsq.dao.ArticleMapper;
import com.baizhi.zsq.entity.Article;
import com.baizhi.zsq.entity.ArticleExample;
import com.baizhi.zsq.entity.Banner;
import com.baizhi.zsq.service.ArticleService;
import com.baizhi.zsq.util.UUIDUtil;
import com.baizhi.zsq.util.UploadPictureUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public HashMap<String, Object> selectArticleByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();

        //总条数
        Integer records = articleMapper.selectCount(null);
        map.put("records", records);
        //总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        //当前页
        map.put("page", page);
        //获取每页展示的数据
        List<Article> ArticleList = articleMapper.selectByExampleAndRowBounds(null, new RowBounds((page - 1) * rows, rows));
        map.put("rows", ArticleList);

        return map;
    }

    /**
     * 添加文章
     *
     * @param article 文章信息
     * @return 返回文章id
     */
    @Override
    public String add(Article article) {
        article.setId(UUIDUtil.getUUID());
        article.setUploadTime(new Date());
        articleMapper.insert(article);
        return article.getId();
    }

    /**
     * 修改文章
     *
     * @param article 文章信息
     * @return 返回文章id
     */
    @Override
    public String oper(Article article) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andIdEqualTo(article.getId());
        if ("".equals(article.getCover())) {
            article.setCover(null);
        }
        articleMapper.updateByExample(article, articleExample);
        return article.getId();
    }

    /**
     * 删除文章
     *
     * @param article 文章信息
     * @return 返回文章id
     */
    @Override
    public String del(Article article) {
        articleMapper.delete(article);
        return article.getId();
    }

    /**
     * 轮播图文件上传
     *
     * @param imgFile
     * @param request
     */
    @Override
    public HashMap<String, Object> articleUpload( MultipartFile imgFile, HttpServletRequest request) {
        System.out.println("===================>"+imgFile.getOriginalFilename());

        /**
         * //成功时
         * {
         *         "error" : 0,
         *         "url" : "http://www.example.com/path/to/file.ext"
         * }
         * //失败时
         * {
         *         "error" : 1,
         *         "message" : "错误信息"
         * }
         */
        HashMap<String, Object> map = new HashMap<>();
        try {
            String name = null;

            //判断文件名是否为空
            if (!"".equals(imgFile.getOriginalFilename())) {
                //根据相对路径获取绝对路径
                String realPath = request.getSession().getServletContext().getRealPath("/article/img");

                //获取文件夹
                File file = new File(realPath);
                //判断文件夹是否存在
                if (!file.exists()) {
                    //创建文件夹
                    file.mkdirs();
                }
                //获取文件名
                String filename = imgFile.getOriginalFilename();

                //给文件加一个时间戳
                name = new Date().getTime() + "-" + filename;

                //文件上传
                try {
                    imgFile.transferTo(new File(realPath, name));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //获取上传   http
            String scheme = request.getScheme();

            //获取   localhost
            String serverName = request.getServerName();

            //获取  端口号  8888
            int serverPort = request.getServerPort();

            //获取  项目名        /项目名
            String contextPath = request.getContextPath();
            String url = scheme + "://" + serverName + ":" + serverPort + contextPath + "/article/img/" + name;
            map.put("error", 0);
            map.put("url", url);

        } catch (Exception e) {
            map.put("error", 0);
            map.put("message", "上传错误");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取文章图片空间
     *
     * @param request
     */
    @Override
    public HashMap<String, Object> uploadInterspace(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<HashMap>  arrayList= new ArrayList<>();

        String realPath = request.getSession().getServletContext().getRealPath("/article/img");
        File file = new File(realPath);
        String[] list = file.list();
        for (int i =0;i<list.length;i++){
            String name = list[i];
            HashMap<String, Object> map1 = new HashMap<>();
            /**
             *       "is_dir": false,       是否是文件夹
             *       "has_file": false,     是否有文件
             *       "filesize": 208736,    文件大小
             *       "dir_path": "",
             *       "is_photo": true,      是否是图片
             *       "filetype": "jpg",     文件后缀
             *       "filename": "1241601537255682809.jpg",     文件名称
             *       "datetime": "2018-06-06 00:36:39"          文件时间
            */
            map1.put("is_dir",false);// 是否是文件夹
            map1.put("has_file",false); // 是否有文件
            long length = new File(realPath, name).length();
            map1.put("filesize",length); //文件大小
            map1.put("is_photo",true);// 是否是图片
            String extension = FilenameUtils.getExtension(name);
            map1.put("filetype",extension);//文件后缀
            map1.put("filename",name);//文件名称

            String[] split = name.split("-");
            long parseLong = Long.parseLong(split[0]);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(parseLong);
            map1.put("datetime",format);//  文件时间
            arrayList.add(map1);
        }

        /**
         *
         *   "moveup_dir_path": "",
         *   "current_dir_path": "",
         *   "current_url": "/ke4/php/../attached/",
         *   "total_count": 5,
         *   "file_list": [
         */

        //获取上传   http
        String scheme = request.getScheme();
        //获取   localhost
        String serverName = request.getServerName();
        //获取  端口号  8888
        int serverPort = request.getServerPort();
        //获取  项目名        /项目名
        String contextPath = request.getContextPath();
        String url = scheme + "://" + serverName + ":" + serverPort + contextPath + "/article/img/";

        map.put("current_url",url);
        map.put("total_count",arrayList.size());
        map.put("file_list",arrayList);

        return map;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Article> selectArticleById(String id) {

        if (id==null){
            //查询所有文章
            List<Article> articles = articleMapper.selectAll();
            return articles;
        }else{
            ArticleExample articleExample = new ArticleExample();
            articleExample.createCriteria().andIdEqualTo(id);
            //根据id查询
            List<Article> articles = articleMapper.selectByExample(articleExample);
            return articles;
        }
    }






}
