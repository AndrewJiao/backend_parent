package com.ypjiao.mettingfilm.backend_cinemas.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ypjiao.mettingfilm.backend_cinemas.controller.vo.CinemasReqVo;
import com.ypjiao.mettingfilm.backend_cinemas.controller.vo.CinemasResVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;

public interface CinemasServiceAPI {

    void cinemasAdd(CinemasReqVo reqVo) throws CommonServiceException;

    IPage<CinemasResVo> cinemasShow(int nowPage,int pageSize) throws CommonServiceException;

}
