package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.FunStar;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/17
*/
public interface FunStarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FunStar record);

    FunStar selectByPrimaryKey(Long id);

    List<FunStar> selectAll();

    int updateByPrimaryKey(FunStar record);
}