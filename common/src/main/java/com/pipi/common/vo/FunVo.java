package com.pipi.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
@Data
public class FunVo implements Serializable {
    private Long userId;
    private String title;
    private String content;
    private List<FunImagesVo> images;
    private int authority;
    private String password;
    private BigDecimal fee;

}
