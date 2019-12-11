package com.baizhi.zsq.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "cmfz_user")//设置成和数据库表命相同
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget("用户表")
public class User {
    @Excel(name = "id" ,height = 20, width = 30, isImportField = "true_st")
    private String id;

    @Excel(name = "手机号" ,height = 20, width = 20, isImportField = "true_st")
    private String phone;

    @Excel(name = "密码" ,height = 20, width = 20, isImportField = "true_st")
    private String password;

    @Excel(name = "盐" ,height = 20, width = 10, isImportField = "true_st")
    private String salt;

    @Excel(name = "状态",replace = { "展示_yes", "冻结_no" } ,height = 20, width = 10, isImportField = "true_st")
    private String status;

    @Excel(name = "图片" ,height = 20, width = 30, isImportField = "true_st",type =2)
    private String picImg;

    @Excel(name = "姓名" ,height = 20, width = 20, isImportField = "true_st")
    private String name;

    @Excel(name = "昵称" ,height = 20, width = 20, isImportField = "true_st")
    @Column(name="legal_name")  //设置成和数据库属性名相同
    private String legalName;

    @Excel(name = "性别" ,height = 20, width = 10, isImportField = "true_st")
    private String sex;

    @Excel(name = "城市",height = 20, width = 20, isImportField = "true_st")
    private String city;
    @Excel(name = "签名",height = 20, width = 20, isImportField = "true_st")
    private String sige;

    @Excel(name = "注册时间",height = 20, width = 20,databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd")
    @Column(name="reg_time")//设置成和数据库属性命相同
    private Date regTime;

    @Excel(name = "上师id",height = 20, width = 20, isImportField = "true_st")
    private String guruId;

}