package com.pipi.common.domain;

import com.pipi.common.enums.BizType;
import com.pipi.common.enums.CheckStatus;
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
 * @create 2019/6/11
 * @desc 短信核验表实体
 **/
@Data
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "check_msg")
public class CheckMsg {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String phone;

    @Column(length = 20)
    private String code;

    @Column(length = 6)
    @Enumerated(EnumType.ORDINAL)
    private CheckStatus checkStatus;

    @Column(length = 6)
    @Enumerated(EnumType.ORDINAL)
    private BizType bizType;

    @Column(nullable = false, updatable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false, name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    public CheckMsg(String phone, String code, BizType bizType) {
        this.phone = phone;
        this.code = code;
        this.bizType = bizType;
        this.checkStatus = CheckStatus.UNCHECK;
    }

}
