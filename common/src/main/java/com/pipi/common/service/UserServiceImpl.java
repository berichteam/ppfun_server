package com.pipi.common.service;

import com.pipi.common.domain.Users;
import com.pipi.common.enums.SocialType;
import com.pipi.common.repository.UserRepository;
import com.pipi.common.service.inter.UserService;
import com.pipi.common.util.PasswordEncryption;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author lazyb
 * @create 2019/5/24
 * @desc 用户接口实现
 **/
@CommonsLog
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users register(String phone, String password) {
        Users user = userRepository.findByPhone(phone);
        if (user != null) {
            log.error("phone is exist");
            return null;
        }
        String name = "ppl" + System.currentTimeMillis();
        return userRepository.save(new Users(name, phone, PasswordEncryption.BCRYPT.encrypt(password)));
    }

    @Override
    public Users registerBySocial(String bindInfo, SocialType socialType) {
        String name = "ppl" + System.currentTimeMillis();
        String password = PasswordEncryption.BCRYPT.encrypt(String.valueOf(System.currentTimeMillis()));
        Users user = new Users(name, password, socialType, bindInfo);
        return userRepository.save(user);
    }

    @Override
    public Users loginBySocial(String bindInfo, SocialType socialType) {
        Users user = userRepository.findByBindInfoAndSocialType(bindInfo, socialType);
        return user;
    }

    @Override
    public Users login(String phone, String password) {
        Users user = userRepository.findByPhone(phone);
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
        return userRepository.findByPhone(phone);
    }

    @Override
    public Page<Users> findAllByPage(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page - 1, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public Users registerByPhoneAndName(String phone, String name, String password) {
        Users pu = userRepository.findByPhone(phone);
        if (pu != null) {
            log.error("phone is exist");
            return null;
        }
        Users nu = userRepository.findByUserName(name);
        if (nu != null) {
            log.error("name is exist");
            return null;
        }
        return userRepository.save(new Users(name, phone, PasswordEncryption.BCRYPT.encrypt(password)));
    }

    @Override
    public Users loginByName(String name, String password) {
        Users user = userRepository.findByUserName(name);
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
        return userRepository.findByUserName(name);
    }
}
