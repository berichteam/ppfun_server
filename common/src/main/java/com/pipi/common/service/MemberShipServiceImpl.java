package com.pipi.common.service;

import com.pipi.common.domain.MemberShipType;
import com.pipi.common.domain.UserMemberShip;
import com.pipi.common.domain.UserOrder;
import com.pipi.common.enums.ProductType;
import com.pipi.common.enums.SocialType;
import com.pipi.common.persistence.mapper.MemberShipTypeMapper;
import com.pipi.common.persistence.mapper.UserMemberShipMapper;
import com.pipi.common.persistence.mapper.UserOrderMapper;
import com.pipi.common.redis.RedisService;
import com.pipi.common.service.inter.MemberShipService;
import lombok.extern.apachecommons.CommonsLog;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author lazyb
 * @create 2019/6/24
 * @desc
 **/
@Service("memberShipService")
@CommonsLog
public class MemberShipServiceImpl implements MemberShipService {

    @Autowired
    private MemberShipTypeMapper memberShipTypeMapper;
    @Autowired
    private UserMemberShipMapper userMemberShipMapper;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private RedisService redisService;

    @Override
    @Transactional
    public Long buyMemberShipAndPreOrder(Integer memberType, Integer amount, Long userId, SocialType socialType) {
        // 通过type查找到membershiptype
        MemberShipType type = memberShipTypeMapper.selectByType(memberType);
        // 先保存用户订单
        UserOrder userOrder = new UserOrder(ProductType.MEMBERSHIP, type.getId(), userId, generateTradeNo(),
                type.getPrice().multiply(new BigDecimal(amount)), socialType, amount);
        userOrderMapper.insert(userOrder);
        // 查出usermembership，如果没有数据就添加，有就判断修改
        UserMemberShip memberShip = userMemberShipMapper.findByUserId(userId);
        if (memberShip == null) {
            memberShip = new UserMemberShip(userId, type.getId(), type.getReservedDays());
            userMemberShipMapper.insert(memberShip);
        } else {
            memberShip.setLastDepositTime(DateTime.now().toDate());
            memberShip.setUpdatedAt(DateTime.now().toDate());
            memberShip.setDeadline(new DateTime(memberShip.getDeadline().getTime()).plusDays(type.getReservedDays()).toDate());
            if (type.getId() > memberShip.getTypeId()) {
                memberShip.setTypeId(type.getId());
            }
            userMemberShipMapper.updateByPrimaryKey(memberShip);
        }
        return userOrder.getId();
    }

    @Override
    public UserMemberShip findByUser(Long userId) {
        return userMemberShipMapper.findByUserId(userId);
    }

    @Override
    public MemberShipType findByTypeId(Long typeId) {
        return memberShipTypeMapper.selectByPrimaryKey(typeId);
    }

    private String generateTradeNo() {
        String time = DateTime.now().toString("yyyyMMddHHmmssSSS");
        Long redisNo = redisService.getRedisSequence();
        String prefix = "";
        for (int i = 0; i < 6 - String.valueOf(redisNo).length(); i++) {
            prefix += "0";
        }
        return time + prefix + redisNo;
    }
}
