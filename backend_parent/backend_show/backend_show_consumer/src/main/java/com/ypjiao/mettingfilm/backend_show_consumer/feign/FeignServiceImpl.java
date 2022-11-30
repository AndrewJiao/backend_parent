package com.ypjiao.mettingfilm.backend_show_consumer.feign;

import com.ypjiao.mettingfilm.backend_utils.pojoDemo.Person;
import org.springframework.stereotype.Service;

/**
 * @author ：jiaojiao
 * @date ：Created in 2021/5/12 1:28
 * @description：feignAPI的实现，用于实现降级处理
 * @modified By：
 */
//注意，实现类需要交给ioc容器使用
@Service
public class FeignServiceImpl implements FeignServiceAPI{
    @Override
    public String sendString(String message) {
        return "sendString方法的降级实现"+message;
    }

    @Override
    public String sendPoJo(Person person) {
        return "sendPoJo方法的降级实现"+person.toString();
    }

    @Override
    public String sendHeader(String header, String pathVariable) {
        return "sendHeader方法的降级实现"+pathVariable;
    }
}
