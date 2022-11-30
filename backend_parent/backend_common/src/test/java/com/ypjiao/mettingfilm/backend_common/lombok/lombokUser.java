package com.ypjiao.mettingfilm.backend_common.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class lombokUser {
    public lombokUser() {

    }
    private String username;
    private String pwd;
    private String code;


}
