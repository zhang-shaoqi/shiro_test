package com.baizhi.zsq.dao;

import com.baizhi.zsq.entity.Album;
import com.baizhi.zsq.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDAO {


    //根据分页查询专辑里的音频              start:起始行      rows:显示多少行
    List<Chapter> selectChapter(@Param("start") Integer start, @Param("rows") Integer rows,@Param("id") String id);

    //获取专辑里的音频总条数
    Integer countChapter(String id);

    //添加音频
    void insertChapter(Chapter chapter);

    //修改轮音频
    void updateChapter(Chapter chapter);

    //删除轮音频
    void deleteChapter(Chapter chapter);

}
