package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.FunContent;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/12
*/
public interface FunContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FunContent record);

    FunContent selectByPrimaryKey(Long id);

    List<FunContent> selectAll();

    int updateByPrimaryKey(FunContent record);
}