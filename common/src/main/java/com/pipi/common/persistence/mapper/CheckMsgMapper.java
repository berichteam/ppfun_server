package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.CheckMsg;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/14
*/
public interface CheckMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CheckMsg record);

    CheckMsg selectByPrimaryKey(Long id);

    List<CheckMsg> selectAll();

    int updateByPrimaryKey(CheckMsg record);
}