package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.FunGift;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/17
*/
public interface FunGiftMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FunGift record);

    FunGift selectByPrimaryKey(Long id);

    List<FunGift> selectAll();

    int updateByPrimaryKey(FunGift record);

    int countByFunId(Long funId);
}