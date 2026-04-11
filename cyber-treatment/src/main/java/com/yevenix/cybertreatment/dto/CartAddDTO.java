package com.yevenix.cybertreatment.dto;

import lombok.Data;

/**
 * 添加药品到购物车DTO
 */
@Data
public class CartAddDTO {
    private Long medicineId;
    private Integer quantity;
}
