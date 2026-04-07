package com.yevenix.cybertreatment.controller;


import com.yevenix.cybertreatment.common.Result;
import com.yevenix.cybertreatment.dto.LoginDTO;
import com.yevenix.cybertreatment.dto.RegisterDTO;
import com.yevenix.cybertreatment.service.UserService;
import com.yevenix.cybertreatment.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * POST /api/user/login
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO){
        LoginVO loginVO = userService.login(loginDTO);
        return Result.success(loginVO);
    }

    /**
     * 用户注册
     * POST /api/user/register
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return Result.success(null);
    }
}
