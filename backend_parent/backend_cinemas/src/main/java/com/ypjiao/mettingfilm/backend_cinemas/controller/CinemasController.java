package com.ypjiao.mettingfilm.backend_cinemas.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ypjiao.mettingfilm.backend_cinemas.controller.vo.CinemasReqVo;
import com.ypjiao.mettingfilm.backend_cinemas.controller.vo.CinemasResVo;
import com.ypjiao.mettingfilm.backend_cinemas.service.CinemasServiceAPI;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BasePageReqVo;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseResponseVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
    @RequestMapping(value = "/cinemas")
public class CinemasController {
    @Autowired
    CinemasServiceAPI serviceAPI;

    @RequestMapping(value = "/cinema:add",method = RequestMethod.POST)
    /**
     * 添加影院
     * 需要新建CinemasReqVo封装获取数据
     * 1. 利用通用mapper存储数据
     * 2. 返回自定义结果
     */
    private BaseResponseVo cinemasAdd(@RequestBody CinemasReqVo reqVo) throws CommonServiceException {
        serviceAPI.cinemasAdd(reqVo);
       return BaseResponseVo.success();
    }

    public BaseResponseVo fallbackMethod1(BasePageReqVo pageVo) throws CommonServiceException{
        /*
            打发票， -》 没打印纸了， 换台机器或者下次再试
            -》 触发告警 -》 告知运维人员，打印发票业务挂了
         */
        // describeCinemas -》 获取影院列表 -> 在Redis中查询影院列表[redis挂了，或者数据没了] -> 获取超时

        // fallback里捕获到超时或者数据内容与分页数不一致

        // fallback就在数据库中查询真实的影院信息

        // 返回一定是成功，或者业务处理失败
        return BaseResponseVo.success();
    }
    @HystrixCommand(
            fallbackMethod = "fallbackMethod1",commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "4000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
            },ignoreExceptions = CommonServiceException.class)
    @RequestMapping(value = "",method = RequestMethod.GET)
    /**
     * 根据给定的页面查询影院
     * 1. 在公共工具模块定义涉及页面的vo的公共部分BasePageReqVo
     * 2， 创建CinemasResVo用于接收查询出来的数据，封装一个转化pojo的方法
     * 3. 通过通用mapper的Page分页查询，查询封装影院结果
     * 4. 将Page对象转化为Map封装返回
     */
    public BaseResponseVo cinemasShow(BasePageReqVo pageVo) throws CommonServiceException {
        if (pageVo.getNowPage()>100){
            throw new CommonServiceException("500","页数过大");
        }
        IPage<CinemasResVo> iPages = serviceAPI.cinemasShow(pageVo.getNowPage(),pageVo.getPageSize());
        assert iPages != null;
        Map<String, Object> cinemas = describeCinemasResponse(iPages, "cinemas");
        return BaseResponseVo.success(cinemas);
    }

    private Map<String, Object> describeCinemasResponse(IPage<CinemasResVo> iPages, String cinemas) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put(cinemas,iPages.getRecords());
        map.put("totalSize",iPages.getPages());
        map.put("totalPages",iPages.getTotal());
        map.put("pageSize",iPages.getSize());
        map.put("nowPage",iPages.getCurrent());
        return map;
    }

}
