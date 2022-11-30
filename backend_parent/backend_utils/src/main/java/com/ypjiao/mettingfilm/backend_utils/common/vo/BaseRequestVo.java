package com.ypjiao.mettingfilm.backend_utils.common.vo;

import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;

public abstract class BaseRequestVo {
    public abstract void checkVoParam() throws CommonServiceException;
}
