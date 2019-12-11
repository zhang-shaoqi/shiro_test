package com.baizhi.zsq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 轮播图实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Banner {

    private String id;//轮播图id
    private String srcImg;//图片路径
    private String description;//描述
    private String status;//状态
    @DateTimeFormat(pattern = "yyyy-MM-dd") //前台到后台的时间格式的转换
    @JsonFormat(pattern = "yyyy-MM-dd")     //后台到前台的时间格式的转换
    private Date uploadTime;//上传时间

}
