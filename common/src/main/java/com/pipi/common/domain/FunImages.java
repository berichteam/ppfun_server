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
public class FunImages implements Serializable {
    private Long id;

    private Long funId;

    private String imageUrl;

    private String imageBlurUrl;

    private String imageSmallUrl;

    private String imageSmallBlurUrl;

    private Integer authority;

    private Integer blur;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public FunImages(String imageUrl, Date createdAt, Date updatedAt) {
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public FunImages(Long id ,Long funId, Integer blur, String description, Date updatedAt) {
        this.id = id;
        this.funId = funId;
        this.blur = blur;
        this.description = description;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", funId=").append(funId);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", imageBlurUrl=").append(imageBlurUrl);
        sb.append(", imageSmallUrl=").append(imageSmallUrl);
        sb.append(", imageSmallBlurUrl=").append(imageSmallBlurUrl);
        sb.append(", authority=").append(authority);
        sb.append(", blur=").append(blur);
        sb.append(", description=").append(description);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}