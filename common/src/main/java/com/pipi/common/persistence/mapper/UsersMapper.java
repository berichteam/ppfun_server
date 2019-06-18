package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* Created by Mybatis Generator 2019/06/18
*/
public interface UsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Users record);

    Users selectByPrimaryKey(Long id);

    List<Users> selectAll();

    int updateByPrimaryKey(Users record);

    Users findByPhone(@Param("phone") String phone);

    Users findByUserName(@Param("userName") String userName);
}