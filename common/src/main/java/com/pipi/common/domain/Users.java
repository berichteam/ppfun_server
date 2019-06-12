package com.pipi.common.domain;

import java.io.Serializable;
import java.util.Date;

import com.pipi.common.enums.UserType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
* Created by Mybatis Generator 2019/06/12
*/
@Getter
@Setter
@ToString
public class Users implements Serializable {
    private Long id;

    private String userName;

    private String password;

    @Column(length = 6, name = "type")
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    private String phone;

    private String bindInfo;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Users(String userName, String phone, String password) {
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.userType = UserType.NORMAL;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", type=").append(userType);
        sb.append(", phone=").append(phone);
        sb.append(", bindInfo=").append(bindInfo);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}