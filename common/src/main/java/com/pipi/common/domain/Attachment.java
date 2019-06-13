package com.pipi.common.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* Created by Mybatis Generator 2019/06/13
*/
@Getter
@Setter
@ToString
public class Attachment implements Serializable {
    private Long id;

    private String attachmentName;

    private String attachmentSuffixAme;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", attachmentName=").append(attachmentName);
        sb.append(", attachmentSuffixAme=").append(attachmentSuffixAme);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}