package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.Fun;
import com.pipi.common.persistence.dto.FunDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* Created by Mybatis Generator 2019/06/12
*/
public interface FunMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(Fun record);

    Fun selectByPrimaryKey(Long id);

    List<Fun> selectAll();

    int updateByPrimaryKey(Fun record);

    int updateByPrimaryKeyPatch(Fun record);

    List<FunDTO> selectFunByAuthority(Integer authority);

    int updateByPrimaryKeyForDelete(Long funId);

    FunDTO selectFunByFunId(Long id);

    List<FunDTO> selectAllFunByPage();

    List<FunDTO> selectMineAllFunByPage(Long userId);

    List<FunDTO> selectAllMineStaredFunByPage(Long userId);

    FunDTO selectFunByFunIdAndPassword(@Param("id") Long id, @Param("password")  String password);
}