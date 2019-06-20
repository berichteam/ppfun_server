package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.UserSocial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* Created by Mybatis Generator 2019/06/18
*/
public interface UserSocialMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserSocial record);

    UserSocial selectByPrimaryKey(Long id);

    List<UserSocial> selectAll();

    int updateByPrimaryKey(UserSocial record);

    UserSocial findBySocialTypeAndOpenId(@Param("socialType") int socialType, @Param("openId") String openId);

    UserSocial findByUserAndSocialType(@Param("userId") Long userId, @Param("socialType") int socialType);

    List<UserSocial> findListByUser(@Param("userId") Long userId);
}