package com.pipi.common.domain;

import java.io.Serializable;
import java.util.Date;

import com.pipi.common.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

/**
* Created by Mybatis Generator 2019/06/18
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Users implements Serializable {
    private Long id;

    private String userName;

    private String password;

    private Short type;

    private String phone;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", type=").append(type);
        sb.append(", phone=").append(phone);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Users(String userName, String phone, String password) {
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.type = Short.valueOf(String.valueOf(UserType.NORMAL.ordinal()));
        this.createdAt = DateTime.now().toDate();
        this.updatedAt = DateTime.now().toDate();
    }

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.type = Short.valueOf(String.valueOf(UserType.NORMAL.ordinal()));
        this.createdAt = DateTime.now().toDate();
        this.updatedAt = DateTime.now().toDate();
    }
}