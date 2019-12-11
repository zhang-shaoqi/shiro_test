package com.baizhi.zsq.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Resource
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      /*  registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/**");*/
    }
}
