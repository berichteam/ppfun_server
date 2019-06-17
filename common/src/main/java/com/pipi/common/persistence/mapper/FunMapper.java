package com.pipi.common.persistence.mapper;

import com.github.pagehelper.Page;
import com.pipi.common.domain.Fun;
import com.pipi.common.persistence.dto.FunDTO;

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

    int updateByPrimaryKeyPatch(Fun record);

    List<FunDTO> selectFunByAuthority(Integer authority);

    int updateByPrimaryKeyForDelete(Integer funId);

    FunDTO selectFunByFunId(Long id);

    List<FunDTO> selectAllFunByPage();
}