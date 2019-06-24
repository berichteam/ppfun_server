package com.pipi.common.enums;

/**
 * @author lazyb
 * @create 2019/6/24
 * @desc
 **/
public enum MemberType {

    WEEK(1),
    MONTH(2),
    YEAR(3);

    private Integer code;

    MemberType(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return this.code;
    }

    public static Integer getCode(String name) {
        for (MemberType item : MemberType.values()) {
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
