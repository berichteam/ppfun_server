package com.pipi.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* Created by Mybatis Generator 2019/06/12
*/
@Getter
@Setter
@ToString
public class Fun implements Serializable {
    private Long id;

    private Long userId;

    private String title;

    private String content;

    private Integer authority;

    private String password;

    private BigDecimal fee;

    @JsonIgnore
    private Integer catalogId;
    @JsonIgnore
    private Integer catalogChildId;
    @JsonIgnore
    private Integer status;
    @JsonIgnore
    private String tag;

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
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", authority=").append(authority);
        sb.append(", password=").append(password);
        sb.append(", fee=").append(fee);
        sb.append(", catalogId=").append(catalogId);
        sb.append(", catalogChildId=").append(catalogChildId);
        sb.append(", status=").append(status);
        sb.append(", tag=").append(tag);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}