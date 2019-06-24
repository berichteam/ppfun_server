package com.pipi.common.service;

import com.pipi.common.enums.MemberType;
import com.pipi.common.persistence.mapper.MemberShipTypeMapper;
import com.pipi.common.persistence.mapper.UserMemberShipMapper;
import com.pipi.common.persistence.mapper.UserOrderMapper;
import com.pipi.common.service.inter.MemberShipService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean buyMemberShipAndPreOrder(MemberType memberType, Integer amount) {
        return false;
    }
}
