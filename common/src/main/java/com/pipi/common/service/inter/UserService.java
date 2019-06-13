package com.pipi.common.service.inter;

import com.pipi.common.domain.Users;
import org.springframework.data.domain.Page;

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
     * 通过用户名登录
     * @param name
     * @return
     */
    Users loginByName(String name, String password);

    /**
     * 通过手机号查找用户
     * @param phone
     * @return
     */
    Users findByPhone(String phone);

    /**
     * 通过用户名查找
     * @param name
     * @return
     */
    Users findByName(String name);

    /**
     * 分页查询所有用户
     * @param page
     * @param size
     * @return
     */
    Page<Users> findAllByPage(Integer page, Integer size);

    /**
     * 通过手机号和用户名注册
     * @param phone
     * @param name
     * @param password
     * @return
     */
    Users registerByPhoneAndName(String phone, String name, String password);

}
