package com.pipi.common.service.inter;

import com.pipi.common.domain.Fun;
import com.pipi.common.vo.FunVo;
import org.springframework.data.domain.Page;

/**
 * @author Ryan
 * @create 2019/5/31
 * @desc 用户文章接口
 **/
public interface FunService {

    Page<Fun> findAllByPage(Integer page, Integer size);

    void funPublish(FunVo funVo);

}
