package com.pipi.common.repository;

import com.pipi.common.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lazyb
 * @create 2019/5/24
 * @desc 用户库
 **/
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    /**
     * 通过用户名获得用户实体
     * @param userName
     * @return
     */
    Users findByUserName(String userName);

    /**
     * 通过手机号获得用户实体
     * @param phone
     * @return
     */
    Users findByPhone(String phone);

}
