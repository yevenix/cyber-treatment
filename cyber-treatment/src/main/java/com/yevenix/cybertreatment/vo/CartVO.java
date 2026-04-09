package com.yevenix.cybertreatment.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CartVO {
    private Long id;
    private Long userId;
    private Long medicineId;
    private String medicineName;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Integer stock;
}
