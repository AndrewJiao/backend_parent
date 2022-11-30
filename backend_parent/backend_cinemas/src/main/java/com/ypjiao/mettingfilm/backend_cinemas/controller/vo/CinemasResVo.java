package com.ypjiao.mettingfilm.backend_cinemas.controller.vo;

import com.ypjiao.mettingfilm.backend_cinemas.dao.entity.MoocCinemaT;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseResponseVo;
import lombok.Data;

@Data
public class CinemasResVo {
    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;
    private String cinemaId;

    public static CinemasResVo pojo2vo(MoocCinemaT moocCinema){
        CinemasResVo cinemasResVo = new CinemasResVo();
        cinemasResVo.setBrandId(moocCinema.getBrandId().toString());
        cinemasResVo.setAreaId(moocCinema.getAreaId().toString());
        cinemasResVo.setHallTypeIds(moocCinema.getHallIds());
        cinemasResVo.setCinemaName(moocCinema.getCinemaName());
        cinemasResVo.setCinemaAddress(moocCinema.getCinemaAddress());
        cinemasResVo.setCinemaTele(moocCinema.getCinemaPhone());
        cinemasResVo.setCinemaImgAddress(moocCinema.getImgAddress());
        cinemasResVo.setCinemaPrice(moocCinema.getMinimumPrice().toString());
        cinemasResVo.setCinemaId(moocCinema.getUuid().toString());
        return cinemasResVo;
    }
}
