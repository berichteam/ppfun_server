package com.pipi.common.service.inter;

import com.pipi.common.domain.Fun;
import org.springframework.data.domain.Page;

/**
 * @author Ryan
 * @create 2019/5/31
 * @desc 用户文章接口
 **/
public interface FunService {
    /**
     * 分页查询所有用户
     * @param page
     * @param size
     * @return
     */
    Page<Fun> findAllByPage(Integer page, Integer size);

}
