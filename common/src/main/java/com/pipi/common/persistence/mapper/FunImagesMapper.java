package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.FunImages;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/12
*/
public interface FunImagesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FunImages record);

    FunImages selectByPrimaryKey(Long id);

    List<FunImages> selectAll();

    int updateByPrimaryKey(FunImages record);
}