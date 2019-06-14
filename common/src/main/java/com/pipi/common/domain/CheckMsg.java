package com.pipi.common.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* Created by Mybatis Generator 2019/06/14
*/
@Getter
@Setter
@ToString
public class CheckMsg implements Serializable {
    private Long id;

    private String phone;

    private String code;

    private Short bizType;

    private Short checkStatus;

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
        sb.append(", phone=").append(phone);
        sb.append(", code=").append(code);
        sb.append(", bizType=").append(bizType);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}