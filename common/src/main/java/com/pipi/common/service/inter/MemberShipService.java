package com.pipi.common.service.inter;

import com.pipi.common.domain.MemberShipType;
import com.pipi.common.domain.UserMemberShip;
import com.pipi.common.enums.SocialType;

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
    Long buyMemberShipAndPreOrder(Integer memberType, Integer amount, Long userId, SocialType socialType);

    /**
     * 通过用户查找会员
     * @param userId
     * @return
     */
    UserMemberShip findByUser(Long userId);

    /**
     * 通过typeid查找类别
     * @param typeId
     * @return
     */
    MemberShipType findByTypeId(Long typeId);

}
