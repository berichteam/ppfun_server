package com.pipi.common.service;

import com.pipi.common.domain.Users;
import com.pipi.common.persistence.mapper.UsersMapper;
import com.pipi.common.service.inter.UserService;
import com.pipi.common.util.PasswordEncryption;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lazyb
 * @create 2019/5/24
 * @desc 用户接口实现
 **/
@CommonsLog
@Service("userService")
public class UserServiceImpl implements UserService {


    private UsersMapper userMapper;

    @Override
    public Users register(String phone, String password) {
        Users user = userMapper.selectByPhone(phone);
        if (user != null) {
            log.error("phone is exist");
            return null;
        }
        String name = "ppl" + System.currentTimeMillis();
        Users users =new Users(name, phone, PasswordEncryption.BCRYPT.encrypt(password));
        int userId =userMapper.insert(users);
        users.setId((Long.valueOf(userId+"")));
        return users;
    }

    @Override
    public Users login(String phone, String password) {
        Users user = userMapper.selectByPhone(phone);
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
        return userMapper.selectByPhone(phone);
    }

    @Override
    public List<Users> findAllByPage(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page - 1, size);
        return userMapper.selectAll();
    }

    @Override
    public Users registerByPhoneAndName(String phone, String name, String password) {
        Users pu = userMapper.selectByPhone(phone);
        if (pu != null) {
            log.error("phone is exist");
            return null;
        }
        Users nu = userMapper.selectByUserName(name);
        if (nu != null) {
            log.error("name is exist");
            return null;
        }

        Users users =new Users(name, phone, PasswordEncryption.BCRYPT.encrypt(password));
        int userId =userMapper.insert(users);
        users.setId((Long.valueOf(userId+"")));
        return users;
    }

    @Override
    public Users loginByName(String name, String password) {
        Users user = userMapper.selectByUserName(name);
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
        return userMapper.selectByUserName(name);
    }
}
