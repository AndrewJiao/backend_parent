package com.ypjiao.testng.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ：jiaojiao
 * @date ：Created in 2021/5/13 0:07
 * @description：http请求模板获取类
 * @modified By：
 */
public class Template {
    private static RestTemplate restTemplate;
    public static RestTemplate getRestTemplate(){
        if (restTemplate==null){
            restTemplate = new RestTemplate();
            ClientHttpRequestInterceptor interceptor = (httpRequest, bytes, clientHttpRequestExecution) -> {
                String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJqZzF2b2siLCJzdWIiOiIzIiwiZXhwIjoxNjIxNDQ0NTY4LCJpYXQiOjE2MjA4Mzk3Njh9.A4r-dkcUCykS9Fn9rKuHzWrEOQjcLMrDCO6FPKdjr7cS_dnUaseAwWXMD8_loshvFquQSSg36-M7S-ZS3fQqFQ";
                HttpHeaders headers = httpRequest.getHeaders();
                headers.set("Authorization",token);
                return clientHttpRequestExecution.execute(httpRequest, bytes);
            };
            //添加header
            ArrayList<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = new ArrayList<>();
            clientHttpRequestInterceptors.add(interceptor);
            restTemplate.setInterceptors(clientHttpRequestInterceptors);
        }
        return restTemplate;
    }


}
