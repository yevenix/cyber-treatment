package com.yevenix.cybertreatment.dto;

import lombok.Data;

/**
 * 更新购物车中某个药品数量DTO
 */
@Data
public class CartUpdateDTO {
    private Long cartId;
    private Integer quantity;
}
