package com.pipi.common.persistence.dto;

import com.pipi.common.domain.Fun;
import com.pipi.common.domain.FunImages;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FunDTO extends Fun implements Serializable {

    private List<FunImages> funImages;

}
