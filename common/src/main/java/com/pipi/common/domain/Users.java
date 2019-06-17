package com.pipi.common.domain;

import com.pipi.common.enums.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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

    @Column(nullable = false, updatable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false, name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<UserSocial> userSocials;

    public Users(String userName, String phone, String password) {
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.userType = UserType.NORMAL;
    }

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.userType = UserType.NORMAL;
    }

}
