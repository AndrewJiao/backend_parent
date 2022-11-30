package com.ypjiao.mettingfilm.backend_film.controller.vo;

import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseRequestVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class FilmSavedVo extends BaseRequestVo {
            private String filmStatus;
            private String filmName;
            private String filmEnName;
            private String mainImgAddress;
            private String filmScore;
            private String filmScorers;
            private String preSaleNum;
            private String boxOffice;
            private String filmTypeId;
            private String filmSourceId;
            private String filmCatIds;
            private String areaId;
            private String dateId;
            private String filmTime;
            private String directorId;
            //验证actid和roleNames数量是否一致
            private String actIds;
            private String roleNames;
            private String filmLength;
            private String biography;
            private String filmImgs;

    @Override
    public void checkVoParam() throws CommonServiceException {
        String[] split = actIds.split("#");
        String[] split1 = roleNames.split("#");
        if (split.length!=split1.length){
            throw  new CommonServiceException("200","演员数量与演员编号不一致");
        }
    }
}
;