package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.GiftBuy;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/27
*/
public interface GiftBuyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GiftBuy record);

    GiftBuy selectByPrimaryKey(Long id);

    List<GiftBuy> selectAll();

    int updateByPrimaryKey(GiftBuy record);
}