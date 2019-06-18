package com.pipi.common.domain;

import java.io.Serializable;
import java.util.Date;

import com.pipi.common.enums.SocialType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
* Created by Mybatis Generator 2019/06/18
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserSocial implements Serializable {
    private Long id;

    private Long userId;

    private String openId;

    private String unionId;

    private String nickName;

    private String gender;

    private String avatarUrl;

    private String city;

    private String province;

    private String country;

    private Short socialType;

    private Date createdAt;

    private Date updatedAt;

    private String sessionKey;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", openId=").append(openId);
        sb.append(", unionId=").append(unionId);
        sb.append(", nickName=").append(nickName);
        sb.append(", gender=").append(gender);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", city=").append(city);
        sb.append(", province=").append(province);
        sb.append(", country=").append(country);
        sb.append(", socialType=").append(socialType);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", sessionKey=").append(sessionKey);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public UserSocial(Long userId, String openId, String sessionKey, SocialType socialType) {
        this.userId = userId;
        this.openId = openId;
        this.sessionKey = sessionKey;
        this.socialType = Short.valueOf(String.valueOf(socialType.ordinal()));
    }
}