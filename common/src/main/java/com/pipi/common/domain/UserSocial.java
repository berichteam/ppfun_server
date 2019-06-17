package com.pipi.common.domain;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.pipi.common.enums.SocialType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lazyb
 * @create 2019/6/17
 * @desc
 **/
@Data
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_social")
public class UserSocial {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String openId;

    @Column(length = 200)
    private String unionId;

    @Column(length = 20)
    private String nickName;

    @Column(length = 2)
    private String gender;

    @Column(length = 200)
    private String avatarUrl;

    @Column(length = 20)
    private String city;

    @Column(length = 20)
    private String province;

    @Column(length = 20)
    private String country;

    @Column(length = 200)
    private String sessionKey;

    @Column(length = 2)
    @Enumerated(EnumType.ORDINAL)
    private SocialType socialType;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public UserSocial(WxMaUserInfo userInfo, Users user) {
        this.openId = userInfo.getOpenId();
        this.unionId = userInfo.getUnionId();
        this.gender = userInfo.getGender();
        this.avatarUrl = userInfo.getAvatarUrl();
        this.city = userInfo.getCity();
        this.province = userInfo.getProvince();
        this.country = userInfo.getCountry();
        this.socialType = SocialType.WECHAT;
        this.user = user;
    }

    public UserSocial(Users user, String openId, String sessionKey, SocialType socialType) {
        this.user = user;
        this.openId = openId;
        this.sessionKey = sessionKey;
        this.socialType = socialType;
    }

}
