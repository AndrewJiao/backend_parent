package com.ypjiao.mettingfilm.backend_hall.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConf {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
