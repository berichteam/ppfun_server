package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.FunView;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/17
*/
public interface FunViewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FunView record);

    FunView selectByPrimaryKey(Long id);

    List<FunView> selectAll();

    int updateByPrimaryKey(FunView record);
}