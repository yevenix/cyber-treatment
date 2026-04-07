package com.yevenix.cybertreatment.service;

import com.yevenix.cybertreatment.dto.LoginDTO;
import com.yevenix.cybertreatment.dto.RegisterDTO;
import com.yevenix.cybertreatment.vo.LoginVO;

public interface UserService {
    /**
     * 用户登录
     * @param loginDTO 登录请求参数
     * @return
     */
    LoginVO login(LoginDTO loginDTO);
    /**
     * 用户注册
     * @param registerDTO 注册请求参数
     */
    void register(RegisterDTO registerDTO);
}
