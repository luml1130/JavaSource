package com.gof._2_behavior._9_Strategy.common;

/**
 * @author luml
 */

public enum PayType {

    ALI(101),
    WECHAT(102),
    UNION(103);

    private PayType(int code) {
        this.code = code;
    }

    private final Integer code;
    public Integer getCode() {
        return code;
    }
}
