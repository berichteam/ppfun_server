package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.CheckMsg;
import org.apache.ibatis.annotations.Param;

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

    List<CheckMsg> findAllByPhoneAndBizTypeAndCheckStatusOrderByCreatedAtDesc(@Param("phone") String phone, @Param("bizType") int bizType,
                                                                              @Param("checkStatus") int checkStatus);

    List<CheckMsg> findAllByPhoneAndBizTypeAndCodeAndCheckStatus(@Param("phone") String phone, @Param("bizType") int bizType,
                                                  @Param("code") String code, @Param("checkStatus") int checkStatus);
}