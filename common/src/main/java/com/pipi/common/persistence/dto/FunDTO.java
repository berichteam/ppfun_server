package com.pipi.common.persistence.dto;

import com.pipi.common.domain.Fun;
import com.pipi.common.domain.FunContent;
import com.pipi.common.domain.FunImages;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class FunDTO extends Fun implements Serializable {

    private FunContent funContent;

    private List<FunImages> funImages;





    public FunDTO(Integer authority, String password, BigDecimal fee, Date createdAt, Date updatedAt) {
        super(authority, password, fee, createdAt, updatedAt);
    }

//    public FunDTO(Integer authority, String password, BigDecimal fee, Date createdAt, Date updatedAt, FunContent funContent) {
//        super(authority, password, fee, createdAt, updatedAt);
//        this.funContent = funContent;
//    }
}
