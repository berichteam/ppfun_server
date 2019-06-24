package com.pipi.common.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

/**
* Created by Mybatis Generator 2019/06/20
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserMemberShip implements Serializable {
    private Long id;

    private Long userId;

    private Long typeId;

    private Integer memberGrade;

    private Date firstDepositTime;

    private Date lastDepositTime;

    private Date deadline;

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
        sb.append(", userId=").append(userId);
        sb.append(", typeId=").append(typeId);
        sb.append(", memberGrade=").append(memberGrade);
        sb.append(", firstDepositTime=").append(firstDepositTime);
        sb.append(", lastDepositTime=").append(lastDepositTime);
        sb.append(", deadline=").append(deadline);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public UserMemberShip(Long userId, Long typeId, Integer days) {
        this.userId = userId;
        this.typeId = typeId;
        this.memberGrade = 1;
        this.firstDepositTime = DateTime.now().toDate();
        this.lastDepositTime = DateTime.now().toDate();
        this.deadline = DateTime.now().plusDays(days).toDate();
        this.createdAt = DateTime.now().toDate();
        this.updatedAt = DateTime.now().toDate();
    }
}