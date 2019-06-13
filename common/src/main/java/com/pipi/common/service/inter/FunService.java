package com.pipi.common.service.inter;

import com.pipi.common.domain.Fun;
import com.pipi.common.persistence.dto.FunDTO;
import com.pipi.common.vo.FunVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Ryan
 * @create 2019/5/31
 * @desc 用户文章接口
 **/
public interface FunService {

    List<FunDTO> findAllByPageAndAuthority(Integer authority);

    void funPublish(FunVo funVo);

}
