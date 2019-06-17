package com.pipi.common.service.inter;

import com.pipi.common.domain.FunGift;
import com.pipi.common.domain.FunStar;
import com.pipi.common.domain.FunView;
import com.pipi.common.persistence.dto.FunDTO;
import com.pipi.common.vo.FunVo;

import java.util.List;

/**
 * @author Ryan
 * @create 2019/5/31
 * @desc 用户文章接口
 **/
public interface FunService {

    List<FunDTO> selectAllByPageAndAuthority(Integer authority);

    void createFun(FunVo funVo);

    void editFun(FunVo funVo);

    int deleteFun(Long funId);

    FunDTO selectFunByFunId(Long funId);

    List<FunDTO> selectAllFunByPage();

    int funStar(FunStar funStar);

    int funView(FunView funView);

    int funGift(FunGift funGift);
}
