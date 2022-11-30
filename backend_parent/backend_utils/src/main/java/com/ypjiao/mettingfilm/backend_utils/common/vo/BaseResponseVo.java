package com.ypjiao.mettingfilm.backend_utils.common.vo;

import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class BaseResponseVo<M> {
    private String code;    //业务编号
    private String message; //异常信息
    private M data;         //业务返回数据

    //成果有参数
    public static<M> BaseResponseVo success(M data) {
        BaseResponseVo baseResponseVo = new BaseResponseVo();
        baseResponseVo.setCode("200");
        baseResponseVo.setMessage("");
        baseResponseVo.setData(data);
        return baseResponseVo;
    }
    //成功无参数
    public static BaseResponseVo success() {
        BaseResponseVo baseResponseVo = new BaseResponseVo();
        baseResponseVo.setCode("200");
        baseResponseVo.setMessage("成功");
        return baseResponseVo;
    }
    //业务报错
    public static BaseResponseVo serviceException(CommonServiceException exception) {
        BaseResponseVo baseResponseVo = new BaseResponseVo();
        baseResponseVo.setCode(exception.getError_code());
        baseResponseVo.setMessage(exception.getError_message());
        return baseResponseVo;
    }
    //登录认证
    public static BaseResponseVo noLogin(){
        BaseResponseVo baseResponseVo = new BaseResponseVo();
        baseResponseVo.setCode("401");
        baseResponseVo.setMessage("请登录");
        return baseResponseVo;
    }
}
