package com.pipi.common.repository;

import com.pipi.common.domain.UserSocial;
import com.pipi.common.domain.Users;
import com.pipi.common.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lazyb
 * @create 2019/6/17
 * @desc
 **/
public interface UserSocialRepository extends JpaRepository<UserSocial, Long> {

    /**
     * 通过社交类型和openid找到绑定账号
     * @param socialType
     * @param openId
     * @return
     */
    UserSocial findBySocialTypeAndOpenId(SocialType socialType, String openId);

    /**
     * 通过用户和社交类型找到绑定账号
     * @param user
     * @param socialType
     * @return
     */
    UserSocial findByUsersAndSocialType(Users user, SocialType socialType);

}
