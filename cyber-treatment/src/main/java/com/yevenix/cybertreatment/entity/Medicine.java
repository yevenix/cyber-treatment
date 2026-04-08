package com.yevenix.cybertreatment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("medicine")
public class Medicine {
    @TableId(type = IdType.AUTO)
    private Long id;    // 药品ID
    private Long pharmacyId;    // 所属药店ID
    private String name;    // 药品名称
    private BigDecimal price;   // 价格
    private String category;    // 分类
    private String usageInstruction;    // 使用说明
    private Integer isPrescription; // 是否处方药：0非处方药 1处方药
    private Integer stock;  // 库存数量
    private String image;   // 药品图片URL
    private Integer sales;  // 销量
    private Integer status; //状态：1上架 0下架
    private LocalDateTime createTime;   // 创建时间
    private LocalDateTime updateTime;   // 更新时间
}
