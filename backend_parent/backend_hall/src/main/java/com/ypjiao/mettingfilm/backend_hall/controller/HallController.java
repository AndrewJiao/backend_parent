package com.ypjiao.mettingfilm.backend_hall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.netflix.discovery.converters.Auto;
import com.ypjiao.mettingfilm.backend_hall.controller.vo.HallPageVo;
import com.ypjiao.mettingfilm.backend_hall.controller.vo.HallReqVo;
import com.ypjiao.mettingfilm.backend_hall.controller.vo.HallRespVo;
import com.ypjiao.mettingfilm.backend_hall.service.HallServiceAPI;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseResponseVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("/hallapi")
@RestController
public class HallController {
    @Autowired
    HallServiceAPI service;

    /**
     * 添加大厅
     * 需要自定义HallReqVo接收请求数据
     * 1. 通过通用Mapper添加向影厅表添加影厅
     * 2. 通过请求中的filmId向电影服务查询电影详细信息，并将查询结果封装为moocHallFilmInfoT，写入对应的影厅电影信息表
     * 3. 返回自定义结果
     */
    @RequestMapping(value = "/halls:add", method = RequestMethod.POST)
    public BaseResponseVo hallAdd(@RequestBody HallReqVo reqVo) throws CommonServiceException {
        service.hallAdd(reqVo);
        return BaseResponseVo.success();
    }

    /**
     * 根据影院id，查询影厅
     * 定义HallPageVo封装接收数据
     * 1. 调用通用Mapper，分页通过影院id为条件查询所有的影厅结果
     * 2. 封装返回结果为Map
     */
    @RequestMapping(value = "/halls", method = RequestMethod.GET)
    public BaseResponseVo hallShow( HallPageVo reqVo){
        IPage<HallRespVo> hallRespVoIPage = service.describeShow(reqVo.getNowPage(), reqVo.getPageSize(), reqVo.getCinemaId());
        HashMap<String, Object> map = discriptActorsResponse(hallRespVoIPage, "halls");
        return BaseResponseVo.success(map);
    }


    private HashMap<String, Object> discriptActorsResponse(IPage page, String title) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put(title,page.getRecords());
        map.put("totalSize",page.getPages());
        map.put("totalPages",page.getTotal());
        map.put("pageSize",page.getSize());
        map.put("nowPage",page.getCurrent());
        return map;
    }

}
