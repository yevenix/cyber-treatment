package com.yevenix.cybertreatment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yevenix.cybertreatment.dto.CartAddDTO;
import com.yevenix.cybertreatment.vo.CartVO;

import java.util.List;

public interface CartService {
    /**
     * 获取购物车列表
     * @param userId
     * @return
     */
    List<CartVO> list(Long userId);

    /**
     * 添加药品到购物车
     * @param cartDTO
     * @param userId
     */
    void add(CartAddDTO cartDTO, Long userId);
}
