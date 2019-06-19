package com.pipi.common.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pipi.common.util.ImagesUrlHandleUtil;
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

    @JsonIgnore
    private Long id;
    @JsonIgnore  //返回时排除掉这个字段
    private Long funId;

    @JsonProperty("uuid")
    private Long attachmentId;

    @JsonProperty("originUrl")
    private String imageUrl;
    @JsonIgnore  //返回时排除掉这个字段
    private Integer authority;
    @JsonIgnore  //返回时排除掉这个字段
    private Integer blur;
    @JsonProperty("desc")  //返回时排除掉这个字段
    private String description;
    @JsonIgnore  //返回时排除掉这个字段
    private Date createdAt;
    @JsonIgnore  //返回时排除掉这个字段
    private Date updatedAt;


    private String bigUrl;
    @JsonSerialize(using = ImagesUrlHandleUtil.class)
    public String getBigUrl() {
        return this.getImageUrl();
    }
    private String smallUrl;
    @JsonSerialize(using = ImagesUrlHandleUtil.class)
    public String getSmallUrl() {
        return this.getImageUrl();
    }

    private Integer originWidth;
    private Integer originHeight;





    private static final long serialVersionUID = 1L;


    public FunImages() {
    }

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

    public FunImages(Long id, Long funId, String imageUrl, String imageBlurUrl, String imageSmallUrl, String imageSmallBlurUrl, Integer authority, Integer blur, String description, Date createdAt, Date updatedAt) {
        this.id = id;
        this.funId = funId;
        this.imageUrl = imageUrl;
        this.authority = authority;
        this.blur = blur;
        this.description = description;
        this.createdAt = createdAt;
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