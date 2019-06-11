package com.pipi.common.repository;

import com.pipi.common.domain.CheckMsg;
import com.pipi.common.enums.BizType;
import com.pipi.common.enums.CheckStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lazyb
 * @create 2019/6/11
 * @desc
 **/
@Repository
public interface CheckMsgRepository extends JpaRepository<CheckMsg, Long> {

    /**
     * 通过手机号和业务类型查找可用的验证码
     * @param phone
     * @param bizType
     * @param checkStatus
     * @return
     */
    List<CheckMsg> findAllByPhoneAndBizTypeAndCheckStatusOrderByCreatedAtDesc(String phone, BizType bizType, CheckStatus checkStatus);

    /**
     * 通过手机号，业务类型和验证码找到所有实体
     * @param phone
     * @param bizType
     * @param Code
     * @return
     */
    List<CheckMsg> findAllByPhoneAndBizTypeAndCodeAndCheckStatus(String phone, BizType bizType, String Code, CheckStatus checkStatus);

}
