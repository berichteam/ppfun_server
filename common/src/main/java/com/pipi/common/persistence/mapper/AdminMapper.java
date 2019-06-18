package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.Admin;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/18
*/
public interface AdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    Admin selectByPrimaryKey(Long id);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);

    Admin selectByName(String name);
}