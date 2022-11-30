package com.ypjiao.mettingfilm.backend_hall.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ypjiao.mettingfilm.apis.film.vo.DescribeFilmRespVo;
import com.ypjiao.mettingfilm.backend_hall.controller.vo.HallReqVo;
import com.ypjiao.mettingfilm.backend_hall.controller.vo.HallRespVo;
import com.ypjiao.mettingfilm.backend_hall.dao.entity.MoocFieldT;
import com.ypjiao.mettingfilm.backend_hall.dao.entity.MoocHallFilmInfoT;
import com.ypjiao.mettingfilm.backend_hall.dao.mapper.MoocFieldTMapper;
import com.ypjiao.mettingfilm.backend_hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.ypjiao.mettingfilm.backend_hall.feignapi.HallToFilmFeignApi;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseResponseVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import com.ypjiao.mettingfilm.backend_utils.utils.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class HallServiceImpl implements HallServiceAPI{
    @Resource
    MoocHallFilmInfoTMapper hallFilmInfoTMapper;
    @Resource
    MoocFieldTMapper fieldTMapper;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LoadBalancerClient balancerClient;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void hallAdd(HallReqVo reqVo) throws CommonServiceException {
        MoocFieldT moocFieldT = new MoocFieldT();
        moocFieldT.setCinemaId(ToolUtils.str2Int(reqVo.getCinemaId()));
        moocFieldT.setFilmId(ToolUtils.str2Int(reqVo.getFilmId()));
        moocFieldT.setBeginTime(reqVo.getBeginTime());
        moocFieldT.setEndTime(reqVo.getEndTime());
        moocFieldT.setHallId(ToolUtils.str2Int(reqVo.getHallTypeId()));
        moocFieldT.setHallName(reqVo.getHallName());
        moocFieldT.setPrice(ToolUtils.str2Int(reqVo.getFilmPrice()));
        fieldTMapper.insert(moocFieldT);

//        MoocHallFilmInfoT moocHallFilmInfoT = describeInserData(reqVo.getFilmId());
        //feign
        MoocHallFilmInfoT moocHallFilmInfoT = describeInserDataByFeign(reqVo.getFilmId());
        hallFilmInfoTMapper.insert(moocHallFilmInfoT);



    }

    private MoocHallFilmInfoT describeInserData(String filmId) {
        ServiceInstance choose = balancerClient.choose("FILM-SERVICE");
        String host = choose.getHost();
        int port = choose.getPort();
        String uri = "/films/"+filmId;
        String url = "http://"+host+":"+port+uri;
        JSONObject jsonObject = restTemplate.getForObject(url, JSONObject.class);
        JSONObject data = jsonObject.getJSONObject("data");
        MoocHallFilmInfoT moocHallFilmInfoT = new MoocHallFilmInfoT();
        moocHallFilmInfoT.setFilmId(data.getIntValue("filmId"));
        moocHallFilmInfoT.setFilmName(data.getString("filmName"));
        moocHallFilmInfoT.setFilmLength(data.getString("filmLength"));
        moocHallFilmInfoT.setFilmCats(data.getString("filmCats"));
        moocHallFilmInfoT.setActors(data.getString("actors"));
        moocHallFilmInfoT.setImgAddress(data.getString("imgAddress"));
        return moocHallFilmInfoT;
    }
    /**
     * 通过feign来实现
     */

    @Autowired
    HallToFilmFeignApi filmApi;
    private MoocHallFilmInfoT describeInserDataByFeign(String filmId) throws CommonServiceException {
        BaseResponseVo<DescribeFilmRespVo> film = filmApi.getFilm(filmId);
        DescribeFilmRespVo data = film.getData();
        if (data!=null||ToolUtils.strIsNull(data.toString())){
            throw new CommonServiceException("500","请求filmid为空");
        }
        //封装查询到的数据
        MoocHallFilmInfoT moocHallFilmInfoT = new MoocHallFilmInfoT();
        moocHallFilmInfoT.setFilmId(ToolUtils.str2Int(data.getFilmId()));
        moocHallFilmInfoT.setFilmName(data.getFilmName());
        moocHallFilmInfoT.setFilmLength(data.getFilmLength());
        moocHallFilmInfoT.setFilmCats(data.getFilmCats());
        moocHallFilmInfoT.setActors(data.getActors());
        moocHallFilmInfoT.setImgAddress(data.getImgAddress());
        return moocHallFilmInfoT;
    }

    @Override
    public IPage<HallRespVo> describeShow(int nowPage, int pageSize, int cinemaId) {
        try {
            Page<HallReqVo> hallRespVoPage = new Page<HallReqVo>();
            hallRespVoPage.setSize(pageSize);
            hallRespVoPage.setCurrent(nowPage);
            QueryWrapper<Object> wrapper = new QueryWrapper<>();
            wrapper.eq("cinema_id",cinemaId);
            IPage<HallRespVo> moocFieldTIPage = fieldTMapper.describeShow(hallRespVoPage, wrapper);
            return moocFieldTIPage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
