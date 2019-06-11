package com.pipi.common.service.inter;

import com.pipi.common.domain.CheckMsg;
import com.pipi.common.enums.BizType;

/**
 * @author lazyb
 * @create 2019/6/11
 * @desc
 **/
public interface CheckMsgService {

    /**
     * 通过手机号和业务类型找到一个可用的验证码
     * @param phone
     * @param bizType
     * @return
     */
    CheckMsg findOneAvailableCode(String phone, BizType bizType);

    /**
     * 核验验证码，成功后修改核验状态
     * @param phone
     * @param code
     * @param bizType
     * @return
     */
    boolean checkCode(String phone, String code, BizType bizType);

    /**
     * 添加一条验证码记录
     * @param phone
     * @param bizType
     * @return
     */
    CheckMsg addOneCode(String phone, BizType bizType);

}
