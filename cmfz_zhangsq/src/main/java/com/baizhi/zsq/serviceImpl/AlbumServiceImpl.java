package com.baizhi.zsq.serviceImpl;


import com.baizhi.zsq.dao.AlbumDAO;
import com.baizhi.zsq.dao.ChapterDAO;
import com.baizhi.zsq.entity.Album;
import com.baizhi.zsq.service.AlbumService;
import com.baizhi.zsq.util.UUIDUtil;
import com.baizhi.zsq.util.UploadPictureUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service    //创建service简单对象
@Transactional  //添加默认事务
public class AlbumServiceImpl implements AlbumService {

    @Resource
    private AlbumDAO albumDAO;

    @Resource
    private ChapterDAO chapterDAO;


    /**
     * 根据分页查询专辑信息
     *
     * @param page  展示当前页
     * @param rows  展示每页条数
     * @return
     */
    @Override
    public HashMap<String,Object> selectAlbumByPage(Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();
        //当前页号  page
        map.put("page",page);
        //总条数  records
        Integer records = albumDAO.countAlbum();
        map.put("records",records);
        //总页数   total
        Integer total = records%rows==0?records/rows:records/rows+1;
        map.put("total",total);
        //当前页的所有数据  rows
        List<Album> albumList = albumDAO.selectAlbumByPage((page - 1) * rows, rows);
        map.put("rows",albumList);
        return map;
    }

    /**
     * 添加专辑
     *
     * @param album     添加专辑的di
     * @return  返回专辑的di
     */
    @Override
    public String add(Album album) {
        album.setId(UUIDUtil.getUUID());
        album.setPubDate(new Date());
        albumDAO.insertAlbum(album);
        return album.getId();
    }


    /**
     * 修改专辑
     *
     * @param album     添加专辑的di
     * @return  返回专辑的di
     */
    @Override
    public String oper(Album album) {
        albumDAO.updateAlbum(album);
        return album.getId();
    }

    /**
     * 删除专辑
     *
     * @param album     添加专辑的di
     * @return  返回专辑的di
     */
    @Override
    public String del(Album album) {
        //判断专辑里是否有音频
        Integer integer = chapterDAO.countChapter(album.getId());
        if(integer==0){
            albumDAO.deleteAlbum(album);
            return album.getId();
        }else {
            return null;
        }


    }

    /**
     * 专辑文件上传
     *
     * @param id
     * @param coverImg
     * @param session
     */
    @Override
    public void albumUpload(String id, MultipartFile coverImg, HttpSession session) {
        //调用上传工具类
        String name = UploadPictureUtil.upload(coverImg, session, "/album/img");
        Album album = new Album();
        album.setCoverImg(name);
        album.setId(id);
        albumDAO.updateAlbum(album);
    }

    /**
     * 根据时间获取最新上传的专辑数据
     *
     * @param number    查询几个
     * @return      返回查询出专辑的数据
     */
    @Override
    public List<Album> selectAlbumByPubDate(Integer number) {
        System.out.println(number);
        List<Album> albums = albumDAO.selectAlbumByPubDate(number);
        return albums;
    }
}
