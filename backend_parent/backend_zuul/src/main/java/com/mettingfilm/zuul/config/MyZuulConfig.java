package com.mettingfilm.zuul.config;

import com.mettingfilm.zuul.filter.JWTFilter;
import com.mettingfilm.zuul.filter.MyZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：jiaojiao
 * @date ：Created in 2021/5/12 14:29
 * @description：过滤器注册配置
 * @modified By：
 */
@Configuration
public class MyZuulConfig {
    @Bean
    MyZuulFilter getZuulFilter(){
        return new MyZuulFilter();
    }
    @Bean
    JWTFilter getJWTfilter(){
        return new JWTFilter();
    }
}
