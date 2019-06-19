package com.pipi.common.persistence.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pipi.common.domain.Fun;
import com.pipi.common.domain.FunImages;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FunDTO extends Fun implements Serializable {

    private int viewCount;
    private int giftCount;
    private int starCount;

    @JsonProperty("images")
    private List<FunImages> funImages;

}
