package com.yevenix.cybertreatment.common;

import lombok.Getter;

/**
 * 统一返回结果状态码
 */
@Getter
public enum ResultCode {
    // 成功
    SUCCESS(200, "success"),
    // 失败
    ERROR(500, "error");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
