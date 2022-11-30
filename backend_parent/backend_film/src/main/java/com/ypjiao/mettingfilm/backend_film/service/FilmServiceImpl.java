package com.ypjiao.mettingfilm.backend_film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ypjiao.mettingfilm.apis.film.vo.DescribeFilmRespVo;
import com.ypjiao.mettingfilm.backend_film.controller.vo.DescribeActorsRespVO;
import com.ypjiao.mettingfilm.backend_film.controller.vo.DescribeFilmsRespVo;
import com.ypjiao.mettingfilm.backend_film.controller.vo.FilmSavedVo;
import com.ypjiao.mettingfilm.backend_film.dao.entity.MoocFilmActorT;
import com.ypjiao.mettingfilm.backend_film.dao.entity.MoocFilmInfoT;
import com.ypjiao.mettingfilm.backend_film.dao.entity.MoocFilmT;
import com.ypjiao.mettingfilm.backend_film.dao.mapper.MoocActorTMapper;
import com.ypjiao.mettingfilm.backend_film.dao.mapper.MoocFilmActorTMapper;
import com.ypjiao.mettingfilm.backend_film.dao.mapper.MoocFilmInfoTMapper;
import com.ypjiao.mettingfilm.backend_film.dao.mapper.MoocFilmTMapper;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import com.ypjiao.mettingfilm.backend_utils.utils.ToolUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class FilmServiceImpl implements FilmServiceAPI{
    @Resource
    MoocActorTMapper moocActorTMapper;
    @Resource
    MoocFilmActorTMapper moocFilmActorTMapper;
    @Resource
    MoocFilmInfoTMapper moocFilmInfoTMapper;
    @Resource
    MoocFilmTMapper moocFilmTMapper;
    @Override
    public IPage<DescribeActorsRespVO> getActors(int nowPage, int pageSize) throws CommonServiceException {
        //moocActorTMapper.selectPage()
        //也可用mybatis默认分页查询功能，但由于只需要查询用户id和name，因此自定义mapper
        return moocActorTMapper.discribeActors(new Page<>(nowPage,pageSize));
    }

    @Override
    public IPage<DescribeFilmsRespVo> getFilms(int nowPage, int pageSize) throws CommonServiceException {
        return moocFilmTMapper.describeFilms(new Page<>(nowPage,pageSize));
    }
    @Override
    public DescribeFilmRespVo getFilm(String filmId) throws CommonServiceException {
        DescribeFilmRespVo describeFilmRespVo = moocFilmTMapper.describeFilmById(filmId);
        return describeFilmRespVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void filmAdd(FilmSavedVo film) throws CommonServiceException {
        try{
            MoocFilmT moocFilmT = new MoocFilmT();
            moocFilmT.setFilmName(film.getFilmName());
            moocFilmT.setFilmType(ToolUtils.str2Int(film.getFilmTypeId()));
            moocFilmT.setImgAddress(film.getMainImgAddress());
            moocFilmT.setFilmScore(film.getFilmScore());
            moocFilmT.setFilmPresalenum(ToolUtils.str2Int(film.getPreSaleNum()));
            moocFilmT.setFilmBoxOffice(ToolUtils.str2Int(film.getBoxOffice()));
            moocFilmT.setFilmSource(ToolUtils.str2Int(film.getFilmSourceId()));
            moocFilmT.setFilmCats(film.getFilmCatIds());
            moocFilmT.setFilmArea(ToolUtils.str2Int(film.getAreaId()));
            moocFilmT.setFilmDate(ToolUtils.str2Int(film.getDateId()));
            LocalDateTime localDateTime = ToolUtils.str2LocalDateTime(film.getFilmTime()+" 00:00:00");
            moocFilmT.setFilmTime(localDateTime);
            moocFilmT.setFilmStatus(ToolUtils.str2Int(film.getFilmStatus()));
            //mybatis会自动返回uuid到实体中
            moocFilmTMapper.insert(moocFilmT);
            MoocFilmInfoT moocFilmInfoT = new MoocFilmInfoT();
            moocFilmInfoT.setFilmId(moocFilmT.getUuid()+"");
            moocFilmInfoT.setFilmEnName(film.getFilmEnName());
            moocFilmInfoT.setFilmScore(film.getFilmScore());
            moocFilmInfoT.setFilmScoreNum(ToolUtils.str2Int(film.getPreSaleNum()));
            moocFilmInfoT.setFilmLength(ToolUtils.str2Int(film.getFilmLength()));
            moocFilmInfoT.setBiography(film.getBiography());
            moocFilmInfoT.setDirectorId(ToolUtils.str2Int(film.getDirectorId()));
            moocFilmInfoT.setFilmImgs(film.getFilmImgs());
            moocFilmInfoTMapper.insert(moocFilmInfoT);

            String[] split = film.getActIds().split("#");
            String[] split1 = film.getRoleNames().split("#");
            for (int i = 0; i < split.length; i++) {
                MoocFilmActorT moocFilmActorT = new MoocFilmActorT();
                moocFilmActorT.setFilmId(moocFilmT.getUuid());
                moocFilmActorT.setActorId(ToolUtils.str2Int(split[i]));
                moocFilmActorT.setRoleName(split1[i]);
                moocFilmActorTMapper.insert(moocFilmActorT);
            }
        }catch (Exception e){
            throw new CommonServiceException("500", e.getMessage());
        }





    }
}
