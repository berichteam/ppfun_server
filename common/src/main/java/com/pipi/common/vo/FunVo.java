package com.pipi.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
@Data
public class FunVo implements Serializable {

    private Long id;

    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private String title;
    @JsonIgnore
    private String content;
    @JsonIgnore
    private List<FunImagesVo> images;
    @JsonIgnore
    private int authority;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private BigDecimal fee;

}
