package com.pipi.common.enums;

/**
 * @author lazyb
 * @create 2019/6/24
 * @desc
 **/
public enum ProductType {

    PRESENT(1),
    MEMBERSHIP(2);

    private Integer code;

    ProductType(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return this.code;
    }

    public static Integer getCode(String name) {
        for (ProductType item : ProductType.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
