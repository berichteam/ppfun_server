package com.pipi.common.service;

import com.pipi.common.domain.CheckMsg;
import com.pipi.common.enums.BizType;
import com.pipi.common.enums.CheckStatus;
import com.pipi.common.persistence.mapper.CheckMsgMapper;
import com.pipi.common.service.inter.CheckMsgService;
import lombok.extern.apachecommons.CommonsLog;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lazyb
 * @create 2019/6/11
 * @desc
 **/
@CommonsLog
@Service("checkMsgService")
public class CheckMsgServiceImpl implements CheckMsgService {

    @Autowired
    private CheckMsgMapper checkMsgMapper;

    @Override
    public CheckMsg findOneAvailableCode(String phone, BizType bizType) {
        List<CheckMsg> clist = checkMsgMapper.findAllByPhoneAndBizTypeAndCheckStatusOrderByCreatedAtDesc(phone,
                bizType.ordinal(), CheckStatus.UNCHECK.ordinal());
        if (clist.isEmpty()) {
            return null;
        } else {
            return clist.get(0);
        }
    }

    @Override
    public boolean checkCode(String phone, String code, BizType bizType) {
        List<CheckMsg> clist = checkMsgMapper.findAllByPhoneAndBizTypeAndCodeAndCheckStatus(phone, bizType.ordinal(),
                code, CheckStatus.UNCHECK.ordinal());
        if (clist.isEmpty()) {
            return false;
        } else {
            for (CheckMsg checkMsg : clist) {
                // 有效期5分钟，判断是否大于5分钟
                if (DateTime.now().minusMinutes(5).isAfter(checkMsg.getCreatedAt().getTime())) {
                    log.error(checkMsg + "有效期大于5分钟，失效！");
                    checkMsg.setCheckStatus(Short.valueOf(String.valueOf(CheckStatus.INVALID.ordinal())));
                    checkMsg.setUpdatedAt(DateTime.now().toDate());
                    checkMsgMapper.updateByPrimaryKey(checkMsg);
                    return false;
                } else {
                    checkMsg.setCheckStatus(Short.valueOf(String.valueOf(CheckStatus.CHECKED.ordinal())));
                    checkMsg.setUpdatedAt(DateTime.now().toDate());
                    checkMsgMapper.updateByPrimaryKey(checkMsg);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public CheckMsg addOneCode(String phone, BizType bizType) {
       CheckMsg res = null;
        boolean flag = false;
        List<CheckMsg> clist = checkMsgMapper.findAllByPhoneAndBizTypeAndCheckStatusOrderByCreatedAtDesc(phone, bizType.ordinal(),
                CheckStatus.UNCHECK.ordinal());
        if (!clist.isEmpty()) {
            CheckMsg checkMsg = clist.get(0);
            // 重发间隔1分钟，判断是否大于1分钟才可重发
            if (DateTime.now().minusMinutes(1).isAfter(checkMsg.getCreatedAt().getTime())) {
                log.info(checkMsg + "发送时间大于1分钟，可重发！");
                flag = true;
            }
        } else {
            flag = true;
        }
        if (flag) {
            // 先将其他验证码都置为失效
            for (CheckMsg ck : clist) {
                ck.setCheckStatus(Short.valueOf(String.valueOf(CheckStatus.INVALID.ordinal())));
                ck.setUpdatedAt(DateTime.now().toDate());
                checkMsgMapper.updateByPrimaryKey(ck);
            }
            // 然后新增一条验证码
            String code = String.valueOf(Double.valueOf((Math.random() * 9 + 1) * 100000).intValue());
            CheckMsg cm = new CheckMsg(phone, code, bizType);
            int ckId = checkMsgMapper.insert(cm);
            res = checkMsgMapper.selectByPrimaryKey(Long.valueOf(ckId));
        }
        return res;
    }
}
