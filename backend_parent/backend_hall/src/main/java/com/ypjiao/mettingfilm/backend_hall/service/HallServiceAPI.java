package com.ypjiao.mettingfilm.backend_hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ypjiao.mettingfilm.backend_hall.controller.vo.HallReqVo;
import com.ypjiao.mettingfilm.backend_hall.controller.vo.HallRespVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;

public interface HallServiceAPI {
    void hallAdd(HallReqVo reqVo) throws CommonServiceException;

    IPage<HallRespVo> describeShow(int nowPage, int pageSize, int cinemaId);
}
