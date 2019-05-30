package com.pipi.common.domain;

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
 * @create 2019/5/30
 * @desc
 **/
@Data
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50, name = "name")
    private String name;

    @Column(length = 200)
    private String password;

    @Column(nullable = false, updatable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false, name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
