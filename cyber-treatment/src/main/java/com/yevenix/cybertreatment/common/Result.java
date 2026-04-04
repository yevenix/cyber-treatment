package com.yevenix.cybertreatment.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    // 状态码
    private int code;
    // 状态信息
    private String msg;
    // 数据
    private T data;

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg()  ;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> error(T data) {
        return new Result<>(ResultCode.ERROR, data);
    }
    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(ResultCode.ERROR.getCode(), msg, data);
    }
}
