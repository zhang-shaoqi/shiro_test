package com.baizhi.zsq;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口类
 */

@tk.mybatis.spring.annotation.MapperScan("com.baizhi.zsq.dao")
@org.mybatis.spring.annotation.MapperScan("com.baizhi.zsq.dao")//自动创建dao实现类对象
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
