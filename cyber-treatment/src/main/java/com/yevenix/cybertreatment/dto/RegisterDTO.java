package com.yevenix.cybertreatment.dto;

import lombok.Data;

/**
 * 注册请求DTO
 */
@Data
public class RegisterDTO {
    private String phone;
    private String password;
    private String nickname;
}
