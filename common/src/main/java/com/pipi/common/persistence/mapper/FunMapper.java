package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.Fun;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/12
*/
public interface FunMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fun record);

    Fun selectByPrimaryKey(Long id);

    List<Fun> selectAll();

    int updateByPrimaryKey(Fun record);
}