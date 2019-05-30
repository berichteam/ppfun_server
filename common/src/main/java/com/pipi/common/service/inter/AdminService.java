package com.pipi.common.service.inter;

import com.pipi.common.domain.Admin;

/**
 * @author lazyb
 * @create 2019/5/30
 * @desc
 **/
public interface AdminService {

    /**
     * 注册一个用户
     *
     * @param name
     * @param password
     */
    Admin register(String name, String password);

    /**
     * 用户登陆操作
     *
     * @param name
     * @param password
     * @return
     */
    Admin login(String name, String password);

    /**
     * 通过用户名找到一个用户
     *
     * @param name
     * @return
     */
    Admin findByName(String name);

}
