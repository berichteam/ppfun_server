package com.pipi.common.service.inter;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.github.pagehelper.PageInfo;
import com.pipi.common.domain.UserSocial;
import com.pipi.common.domain.Users;
import com.pipi.common.enums.SocialType;
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
     * 通过社交注册
     * @param bindInfo
     * @param socialType
     * @return
     */
    Users registerBySocial(String bindInfo, SocialType socialType, String sessionKey);

    /**
     * 通过社交登录
     * @param bindInfo
     * @param socialType
     * @return
     */
    Users loginBySocial(String bindInfo, SocialType socialType, String sessionKey);

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
    PageInfo<Users> findAllByPage(Integer page, Integer size);

    /**
     * 通过手机号和用户名注册
     * @param phone
     * @param name
     * @param password
     * @return
     */
    Users registerByPhoneAndName(String phone, String name, String password);

    /**
     * 通过微信用户信息来更新用户
     * @param user
     * @param userInfo
     * @return
     */
    UserSocial updateBySocial(Users user, WxMaUserInfo userInfo, SocialType socialType);

    /**
     * 通过用户和社交来查找
     * @param user
     * @param socialType
     * @return
     */
    UserSocial findByUser(Users user, SocialType socialType);

    /**
     * 删除社交信息
     * @param user
     * @param socialType
     * @return
     */
    int delBySocial(Users user, SocialType socialType);

    /**
     * 更新用户手机号
     * @param user
     * @param phone
     * @return
     */
    int updatePhoneBySocial(Users user, String phone);

}
