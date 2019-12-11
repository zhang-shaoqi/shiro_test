package com.baizhi.zsq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "cmfz_article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private String id;

    private String title;

    @Column(name="upload_time")
    private Date uploadTime;

    @Column(name="guru_name")
    private String guruName;

    private String comtent;

    @Column(name="guru_id")
    private String guruId;

    private String cover;

    private String status;

}