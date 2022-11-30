package com.ypjiao.mettingfilm.controller.vo;

import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseRequestVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import com.ypjiao.mettingfilm.backend_utils.utils.ToolUtils;
import lombok.Data;

@Data
public class LoginReqVo extends BaseRequestVo {
    public String username;
    public String password;

    /**
     * 验证请求参数合法性
     * @throws CommonServiceException
     */
    @Override
    public void checkVoParam() throws CommonServiceException {
        if (ToolUtils.strIsNull(username)||ToolUtils.strIsNull(password)){
            CommonServiceException commonServiceException = new CommonServiceException();
            commonServiceException.setError_code("404");
            commonServiceException.setError_message("name或password不能为空");
            throw commonServiceException;
        }
    }
}
