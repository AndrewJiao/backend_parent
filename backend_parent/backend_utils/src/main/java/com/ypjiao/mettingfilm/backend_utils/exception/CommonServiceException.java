package com.ypjiao.mettingfilm.backend_utils.exception;

import lombok.Builder;
import lombok.Data;

@Data
public class CommonServiceException extends Exception{
    String error_code;
    String error_message;

    public CommonServiceException(String error_code, String error_message) {
        this.error_code = error_code;
        this.error_message = error_message;
    }

    public CommonServiceException() {
    }
}
