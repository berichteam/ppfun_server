package com.pipi.common.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* Created by Mybatis Generator 2019/06/12
*/
@Getter
@Setter
@ToString
public class FunContent implements Serializable {
    private Long id;

    private Long funId;

    private String title;

    private String content;

    private Integer authority;

    private String password;

    private Date createdAt;

    private Date updateAt;

    private static final long serialVersionUID = 1L;

    public FunContent(Long funId, String title, String content, Date createdAt, Date updateAt) {
        this.funId = funId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", funId=").append(funId);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", authority=").append(authority);
        sb.append(", password=").append(password);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}