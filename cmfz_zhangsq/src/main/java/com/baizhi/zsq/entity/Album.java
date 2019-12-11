package com.baizhi.zsq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *专辑实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private String id;  //专辑id
    private String title; //标题
    private String coverImg; //封面
    private String score;   //评分
    private String author; //作者
    private String broadcast;   //播音
    private int count;   //集数
    private String countent;    //内容
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pubDate;   //发表日期

}
