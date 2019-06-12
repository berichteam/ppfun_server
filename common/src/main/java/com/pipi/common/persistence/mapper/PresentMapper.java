package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.Present;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/12
*/
public interface PresentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Present record);

    Present selectByPrimaryKey(Integer id);

    List<Present> selectAll();

    int updateByPrimaryKey(Present record);
}