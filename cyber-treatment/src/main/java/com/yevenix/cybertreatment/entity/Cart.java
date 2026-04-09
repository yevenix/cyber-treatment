package com.yevenix.cybertreatment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("cart")
public class Cart {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long medicineId;
    private Integer quantity;   // 用户购买的数量
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
