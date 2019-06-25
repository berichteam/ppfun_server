package com.pipi.common.service.inter;

import com.pipi.common.enums.MemberType;

/**
 * @author lazyb
 * @create 2019/6/24
 * @desc
 **/
public interface MemberShipService {

    /**
     * 购买会员，并预下单
     * @param memberType 会员类型
     * @param amount 数量
     * @return
     */
    boolean buyMemberShipAndPreOrder(MemberType memberType, Integer amount, Long userId);

}
