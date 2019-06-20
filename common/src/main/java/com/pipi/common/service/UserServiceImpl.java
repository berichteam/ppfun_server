package com.pipi.common.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.github.pagehelper.PageInfo;
import com.pipi.common.domain.UserSocial;
import com.pipi.common.domain.Users;
import com.pipi.common.enums.SocialType;
import com.pipi.common.persistence.mapper.UserSocialMapper;
import com.pipi.common.persistence.mapper.UsersMapper;
import com.pipi.common.service.inter.UserService;
import com.pipi.common.util.PasswordEncryption;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lazyb
 * @create 2019/5/24
 * @desc 用户接口实现
 **/
@CommonsLog
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserSocialMapper userSocialMapper;

    @Override
    public Users register(String phone, String password) {
        Users user = usersMapper.findByPhone(phone);
        if (user != null) {
            log.error("phone is exist");
            return null;
        }
        String name = "ppl" + System.currentTimeMillis();
        user = new Users(name, phone, PasswordEncryption.BCRYPT.encrypt(password));
        usersMapper.insert(user);
        return usersMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public Users registerBySocial(String bindInfo, SocialType socialType, String sessionKey) {
        String name = "ppl" + System.currentTimeMillis();
        String password = PasswordEncryption.BCRYPT.encrypt(String.valueOf(System.currentTimeMillis()));
        Users user = new Users(name, password);
        usersMapper.insert(user);
        user = usersMapper.selectByPrimaryKey(user.getId());
        UserSocial userSocial = new UserSocial(user.getId(), bindInfo, sessionKey, socialType);
        userSocialMapper.insert(userSocial);
        return user;
    }

    @Override
    public Users loginBySocial(String bindInfo, SocialType socialType, String sessionKey) {
        UserSocial userSocial = userSocialMapper.findBySocialTypeAndOpenId(socialType.ordinal(), bindInfo);
        if (userSocial != null) {
            userSocial.setSessionKey(sessionKey);
            userSocialMapper.updateByPrimaryKey(userSocial);
            return usersMapper.selectByPrimaryKey(userSocial.getUserId());
        } else {
            return null;
        }
    }

    @Override
    public Users login(String phone, String password) {
        Users user = usersMapper.findByPhone(phone);
        if (user == null) {
            log.error("phone is error");
            return null;
        }
        if (!PasswordEncryption.BCRYPT.check(password, user.getPassword())) {
            log.error("password is error");
            return null;
        }
        return user;
    }

    @Override
    public Users findByPhone(String phone) {
        return usersMapper.findByPhone(phone);
    }

    @Override
    public PageInfo<Users> findAllByPage(Integer page, Integer size) {
        List<Users> ulist = usersMapper.selectAll();
        return new PageInfo<>(ulist);
    }

    @Override
    public Users registerByPhoneAndName(String phone, String name, String password) {
        Users pu = usersMapper.findByPhone(phone);
        if (pu != null) {
            log.error("phone is exist");
            return null;
        }
        Users nu = usersMapper.findByUserName(name);
        if (nu != null) {
            log.error("name is exist");
            return null;
        }
        Users user = new Users(name, phone, PasswordEncryption.BCRYPT.encrypt(password));
        usersMapper.insert(user);
        return usersMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public Users loginByName(String name, String password) {
        Users user = usersMapper.findByUserName(name);
        if (user == null) {
            log.error("name is error");
            return null;
        }
        if (!PasswordEncryption.BCRYPT.check(password, user.getPassword())) {
            log.error("password is error");
            return null;
        }
        return user;
    }

    @Override
    public Users findByName(String name) {
        return usersMapper.findByUserName(name);
    }

    @Override
    public UserSocial updateBySocial(Users user, WxMaUserInfo userInfo, SocialType socialType) {
        UserSocial userSocial = userSocialMapper.findByUserAndSocialType(user.getId(), socialType.ordinal());
        userSocial.setNickName(userInfo.getNickName());
        userSocial.setAvatarUrl(userInfo.getAvatarUrl());
        userSocial.setCity(userInfo.getCity());
        userSocial.setProvince(userInfo.getProvince());
        userSocial.setCountry(userInfo.getCountry());
        userSocial.setGender(userInfo.getGender());
        userSocial.setOpenId(userInfo.getOpenId());
        userSocial.setUnionId(userInfo.getUnionId());
        userSocialMapper.updateByPrimaryKey(userSocial);
        return userSocial;
    }

    @Override
    public UserSocial findByUser(Users user, SocialType socialType) {
        return userSocialMapper.findByUserAndSocialType(user.getId(), socialType.ordinal());
    }

    @Override
    public int delBySocial(Users user, SocialType socialType) {
        UserSocial userSocial = userSocialMapper.findByUserAndSocialType(user.getId(), socialType.ordinal());
        return userSocialMapper.deleteByPrimaryKey(userSocial.getId());
    }

    @Override
    public int updatePhoneBySocial(Users user, String phone) {
        user.setPhone(phone);
        return usersMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<UserSocial> findListByUser(Users user) {
        return userSocialMapper.findListByUser(user.getId());
    }

    @Override
    public List<Users> findListByIds(String ids) {
        String[] idArray = ids.split(",");
        List<Users> ulist = new ArrayList<>();
        for (String id : idArray) {
            ulist.add(usersMapper.selectByPrimaryKey(Long.valueOf(id)));
        }
        return ulist;
    }
}
