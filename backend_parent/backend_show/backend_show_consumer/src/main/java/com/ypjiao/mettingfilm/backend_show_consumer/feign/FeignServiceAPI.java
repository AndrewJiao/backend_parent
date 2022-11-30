package com.ypjiao.mettingfilm.backend_show_consumer.feign;

import com.ypjiao.mettingfilm.backend_utils.pojoDemo.Person;
import com.ypjiao.mettingfilm.config.MyFeignConfig;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
//Feign的基础设置，url用来指定默认服务器
/*@FeignClient(value = "feignTest", url = "http://localhost:7401",
        path = "/provider",
        primary = true
)*/
//启用自定义Feign配置后，例如本配置启用feign默认合约，因此不再支持SpringMvc相关注解
/*@FeignClient(value = "feignTest", url = "http://localhost:7401",
        path = "/provider",
        configuration = MyFeignConfig.class,
        primary = true)*/
//feign内部已经整合了ribbon，ribbon整合了eureka，因此当没有指定url时，feign会依据value从serveList中依据ribbon的轮询规则找到对应的服务
@FeignClient(value = "hello-eureka-provider",
        path = "/provider",
        primary = true
        //feign整合降级需要指定降级处理类，也就是feign的实现类，同时需要将feign接口类的primay指定配true
        //注意feign.hystrix.enable要设为true，开启hystrix
        ,fallback = FeignServiceImpl.class
)
public interface FeignServiceAPI {
    @RequestMapping(value = "/provid_hello")
    String sendString(@RequestParam String message);
    @RequestMapping(value = "/privide_POJO_hello",method = RequestMethod.POST)
    String sendPoJo(@RequestBody Person person);
    @RequestMapping(value = "/privide_header_hello/{id}",method = RequestMethod.POST)
    String sendHeader(@RequestHeader(value = "header") String header,@PathVariable(value = "id") String pathVariable);
    //feign默认注解
//    @RequestLine("GET /privide_POJO_hello?message={message}")
//    String sendPoJo(@Param("message") Person person);
}

