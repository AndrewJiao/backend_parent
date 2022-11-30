package com.ypjiao.mettingfilm.backend_cinemas.controller.vo;

import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseRequestVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class CinemasReqVo extends BaseRequestVo {
    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;

    @Override
    public void checkVoParam() throws CommonServiceException {
        //TODO paramCheck
    }
}
