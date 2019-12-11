package com.baizhi.zsq.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 文件上传工具类
 */
public class UploadPictureUtil {

    /**
     *
     * @param srcImg     上传的图片
     * @param session   session作用域
     * @param url       文件上传路径
     * @return          返回图片名称
     */
    public static String upload(MultipartFile srcImg, HttpSession session,String url){
        String name=null;
        if(!"".equals(srcImg.getOriginalFilename())) try {
            //通过相对路径获取绝对路径                          //url：文件存储路径
            String realPath = session.getServletContext().getRealPath(url);
            //获取文件夹
            File file = new File(realPath);
            //判断文件夹是否存在
            if (!file.exists()) {
                //创建文件夹
                file.mkdirs();
            }
            //获取文件名
            String filename = srcImg.getOriginalFilename();
            //给文件加一个时间戳
            name = new Date().getTime() + "-" + filename;

            File file1 = new File(realPath, name);
            System.out.println("================"+file1.length());
            //上传文件
            srcImg.transferTo(new File(realPath, name));

            int i=0;
            do {

                System.out.println(srcImg.getSize());
                System.out.println(file1.length());
                i++;
            }while (i<20);




        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回图片名称
        return name;
    }
}
