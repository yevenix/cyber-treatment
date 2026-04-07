package com.yevenix.cybertreatment.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;    // 用户ID
    private String phone;   // 手机号
    private String password;    // 密码
    private String nickname;    // 昵称
    @TableField("real_name")
    private String realName;    // 真实姓名
    private String avatar;  // 头像
    private BigDecimal balance; // 账户余额
    private Integer status; // 状态：1正常，0禁用
    private LocalDateTime createTime;   // 创建时间
    private LocalDateTime updateTime;   // 更新时间
}
