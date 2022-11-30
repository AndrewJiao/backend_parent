package com.ypjiao.mettingfilm.backend_hall.controller.vo;

import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseRequestVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class HallReqVo extends BaseRequestVo {

    private String cinemaId;
    private String filmId;
    private String hallTypeId;
    private String beginTime;
    private String endTime;
    private String filmPrice;
    private String hallName;


    @Override
    public void checkVoParam() throws CommonServiceException {

    }
}
