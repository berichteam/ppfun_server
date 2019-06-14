package com.pipi.common.domain;

import com.pipi.common.enums.SocialType;
import com.pipi.common.enums.UserType;
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
 * @create 2019/5/22
 * @desc 用户实体
 **/
@Data
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50, name = "user_name")
    private String userName;

    @Column(length = 200)
    private String password;

    @Column(length = 6, name = "type")
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    @Column(length = 20)
    private String phone;

    @Column(length = 100, name = "bind_info")
    private String bindInfo;

    @Column(nullable = false, updatable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false, name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    @Column(length = 1, name = "social_type")
    @Enumerated(EnumType.ORDINAL)
    private SocialType socialType;

    @Column(length = 200)
    private String avatar;

    public Users(String userName, String phone, String password) {
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.userType = UserType.NORMAL;
    }

    public Users(String userName, String password, SocialType socialType, String bindInfo) {
        this.userName = userName;
        this.password = password;
        this.userType = UserType.NORMAL;
        this.socialType = socialType;
        this.bindInfo = bindInfo;
    }

}
