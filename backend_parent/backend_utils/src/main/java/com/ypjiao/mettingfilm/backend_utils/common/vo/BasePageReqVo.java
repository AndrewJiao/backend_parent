package com.ypjiao.mettingfilm.backend_utils.common.vo;

import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class BasePageReqVo extends BaseRequestVo{
    private int nowPage;
    private int pageSize;
    @Override
    public void checkVoParam() throws CommonServiceException {
//        if (nowPage.length()<1||"".equals(nowPage)||pageSize.length()<1||"".equals(pageSize)){
//            // TO DO
//            throw new CommonServiceException("200","param nowPage or pageSize is null");
//        }
    }
}
