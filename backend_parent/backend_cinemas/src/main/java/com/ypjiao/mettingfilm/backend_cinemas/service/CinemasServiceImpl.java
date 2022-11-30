package com.ypjiao.mettingfilm.backend_cinemas.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ypjiao.mettingfilm.backend_cinemas.controller.vo.CinemasReqVo;
import com.ypjiao.mettingfilm.backend_cinemas.controller.vo.CinemasResVo;
import com.ypjiao.mettingfilm.backend_cinemas.dao.entity.MoocCinemaT;
import com.ypjiao.mettingfilm.backend_cinemas.dao.mapper.MoocCinemaTMapper;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import com.ypjiao.mettingfilm.backend_utils.utils.ToolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CinemasServiceImpl implements CinemasServiceAPI{
    @Resource
    MoocCinemaTMapper mapper;
    @Override
    public void cinemasAdd(CinemasReqVo reqVo) throws CommonServiceException {
        try {
            MoocCinemaT moocCinemaT = new MoocCinemaT();
            moocCinemaT.setCinemaName(reqVo.getCinemaName());
            moocCinemaT.setCinemaPhone(reqVo.getCinemaTele());
            moocCinemaT.setBrandId(ToolUtils.str2Int(reqVo.getBrandId()));
            moocCinemaT.setAreaId(ToolUtils.str2Int(reqVo.getAreaId()));
            moocCinemaT.setHallIds(reqVo.getHallTypeIds());
            moocCinemaT.setImgAddress(reqVo.getCinemaImgAddress());
            moocCinemaT.setCinemaAddress(reqVo.getCinemaAddress());
            moocCinemaT.setMinimumPrice(ToolUtils.str2Int(reqVo.getCinemaPrice()));
            mapper.insert(moocCinemaT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IPage<CinemasResVo> cinemasShow(int nowPage, int pageSize) throws CommonServiceException {
        Page<MoocCinemaT> objectPage = new Page<>();
        IPage<MoocCinemaT> moocCinemaTIPage = mapper.selectPage(objectPage, null);
        List<CinemasResVo> collect = moocCinemaTIPage.getRecords().stream().map((entity) -> CinemasResVo.pojo2vo(entity)).collect(Collectors.toList());
        Page<CinemasResVo> cinemasResVoPage = new Page<>();
        cinemasResVoPage.setRecords(collect);
        cinemasResVoPage.setCurrent(nowPage);
        cinemasResVoPage.setSize(pageSize);
        return cinemasResVoPage;
    }
}
