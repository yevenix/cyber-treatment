package com.yevenix.cybertreatment.config;

import com.yevenix.cybertreatment.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 统一处理程序中抛出的异常，返回规范的错误信息
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     *
     * @param e 异常对象
     * @return 统一格式的错误响应
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("系统异常", e);
        return Result.error("系统繁忙，请稍后再试");
    }

    /**
     * 处理业务异常
     * @param e 运行时异常
     * @return 统一格式的错误响应
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<String> handleRuntimeException(RuntimeException e) {
        log.error("业务异常", e);
        return Result.error(e.getMessage());
    }

}
