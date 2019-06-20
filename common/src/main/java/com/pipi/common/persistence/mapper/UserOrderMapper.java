package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.UserOrder;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/20
*/
public interface UserOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserOrder record);

    UserOrder selectByPrimaryKey(Long id);

    List<UserOrder> selectAll();

    int updateByPrimaryKey(UserOrder record);
}