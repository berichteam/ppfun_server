package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.MemberShipType;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/20
*/
public interface MemberShipTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberShipType record);

    MemberShipType selectByPrimaryKey(Long id);

    List<MemberShipType> selectAll();

    int updateByPrimaryKey(MemberShipType record);
}