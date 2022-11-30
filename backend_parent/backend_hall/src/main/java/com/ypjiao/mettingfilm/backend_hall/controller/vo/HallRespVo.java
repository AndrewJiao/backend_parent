package com.ypjiao.mettingfilm.backend_hall.controller.vo;

import lombok.Data;

@Data
public class HallRespVo {
    private String cinemaName;
    private String hallName;
    private String filmName;
    private String hallTypeName;
    private String beginTime;
    private String endTime;
    private String filmPrice;
}
