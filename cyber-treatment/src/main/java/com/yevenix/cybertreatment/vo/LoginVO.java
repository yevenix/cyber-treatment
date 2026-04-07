package com.yevenix.cybertreatment.vo;

import lombok.Data;

@Data
public class LoginVO {
    private String token;   // 认证令牌（JWT）
    private Long userId;    // 用户ID
    private String nickname;    // 昵称
}
