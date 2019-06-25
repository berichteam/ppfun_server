package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.UserMemberShip;

import java.util.List;

/**
* Created by Mybatis Generator 2019/06/25
*/
public interface UserMemberShipMapper {
    int insert(UserMemberShip record);

    List<UserMemberShip> selectAll();

    int updateByPrimaryKey(UserMemberShip record);

    UserMemberShip selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);
}