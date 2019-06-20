package com.pipi.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
* Created by Mybatis Generator 2019/06/20
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberShipType implements Serializable {
    private Long id;

    private Integer memberType;

    private String desc;

    private BigDecimal price;

    private Integer reservedDays;

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
        sb.append(", memberType=").append(memberType);
        sb.append(", desc=").append(desc);
        sb.append(", price=").append(price);
        sb.append(", reservedDays=").append(reservedDays);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}