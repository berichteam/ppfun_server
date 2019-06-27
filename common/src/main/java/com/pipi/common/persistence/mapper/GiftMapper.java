package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.Gift;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/27
*/
public interface GiftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gift record);

    Gift selectByPrimaryKey(Integer id);

    List<Gift> selectAll();

    int updateByPrimaryKey(Gift record);
}