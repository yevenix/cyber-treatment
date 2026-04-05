package com.yevenix.cybertreatment.controller;

import com.yevenix.cybertreatment.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("hello world");
    }
}
