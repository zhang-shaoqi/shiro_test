package com.baizhi.zsq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 音频实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {

    private String id;  //音频id
    private String title;   //标题
    private String src; //音频路径
    private String duration;    //时长
    private String size;    //大小
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date uploadTime;  //上传时间
    private String albumId; //专辑id



}
