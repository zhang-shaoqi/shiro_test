package com.baizhi.zsq.dao;

import com.baizhi.zsq.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDAO {

    //根据分页查询专辑              start:起始行      rows:显示多少行
    List<Album> selectAlbumByPage(@Param("start") Integer start,@Param("rows") Integer rows);

    //获取专辑总条数
    Integer countAlbum();

    //添加专辑
    void insertAlbum(Album album);

    //修改轮播图
    void updateAlbum(Album album);

    //删除轮播图
    void deleteAlbum(Album album);

    //根据时间获取最新上传的专辑数据
    List<Album> selectAlbumByPubDate(@Param("number")Integer number);
}
