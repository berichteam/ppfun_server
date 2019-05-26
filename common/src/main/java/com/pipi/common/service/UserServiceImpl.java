package com.pipi.common.service;

import com.pipi.common.domain.Users;
import com.pipi.common.repository.UserRepository;
import com.pipi.common.service.inter.UserService;
import com.pipi.common.util.PasswordEncryption;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
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
        String name = "jsl" + System.currentTimeMillis();
        return userRepository.save(new Users(name, phone, PasswordEncryption.BCRYPT.encrypt(password)));
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

}
