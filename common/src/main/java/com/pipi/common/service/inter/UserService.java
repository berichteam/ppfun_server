package com.pipi.common.service.inter;

import com.pipi.common.domain.Users;

/**
 * @author lazyb
 * @create 2019/5/24
 * @desc 用户服务接口
 **/
public interface UserService {

    /**
     * 用户注册
     * @param phone
     * @param password
     * @return
     */
    Users register(String phone, String password);

    /**
     * 用户登陆
     * @param phone
     * @param password
     * @return
     */
    Users login(String phone, String password);

    /**
     * 通过手机号查找用户
     * @param phone
     * @return
     */
    Users findByPhone(String phone);

}
