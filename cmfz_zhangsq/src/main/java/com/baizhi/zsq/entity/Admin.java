package com.baizhi.zsq.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    private String id;//管理员id
    private String username;//用户名
    private String password;//密码

}
