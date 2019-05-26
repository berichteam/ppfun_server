package com.pipi.common.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@NoArgsConstructor
@Data
@ToString
public class Error implements Serializable {
    // Error code
    private int code;

    // Error message
    private String message;

}
