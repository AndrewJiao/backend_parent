package com.ypjiao.mettingfilm.backend_show_consumer.ribbon;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.HttpClientRequest;
import com.netflix.niws.client.http.RestClient;

import java.net.URI;

public class App {

    public static void main(String[] args) throws Exception {
        //读取配置文件
        ConfigurationManager.loadPropertiesFromResources("ribbon.properties");  // 1
        System.err.println(ConfigurationManager.getConfigInstance().getProperty("ribbon-client.ribbon.listOfServers"));

        /**
         * 用默认的list配置请求
         */
        RestClient client = (RestClient) ClientFactory.getNamedClient("ribbon-client");  // 2
        //生成http请求
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build(); // 3
        for (int i = 0; i < 5; i++) {
            HttpResponse response = client.executeWithLoadBalancer(request); // 4
            System.err.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
        }
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
        System.err.println(lb.getLoadBalancerStats());
        /**
         * 动态修改serverList，一般于eurekaService注册中心比较适配，
         * 但也可以于ngnix等其它注册中心一起使用
         */
        //手动修改访问配置
        ConfigurationManager.getConfigInstance().setProperty(
                "ribbon-client.ribbon.listOfServers", "www.qq.com:80,www.taobao.com:80"); // 5
        System.err.println("changing servers ...");
        Thread.sleep(3000); // 6
        for (int i = 0; i < 5; i++) {
            HttpResponse response = client.executeWithLoadBalancer(request);
            System.err.println("Status code for " + response.getRequestedURI() + "  : " + response.getStatus());
        }
        System.out.println(lb.getLoadBalancerStats()); // 7
    }

}
