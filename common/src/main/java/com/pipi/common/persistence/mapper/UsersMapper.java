package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.Users;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/12
*/
public interface UsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Users record);

    Users selectByPrimaryKey(Long id);

    List<Users> selectAll();

    int updateByPrimaryKey(Users record);

    Users selectByPhone(String phone);

    Users selectByUserName(String userName);
}