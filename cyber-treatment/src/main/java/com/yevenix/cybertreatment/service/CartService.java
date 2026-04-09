package com.yevenix.cybertreatment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yevenix.cybertreatment.vo.CartVO;

import java.util.List;

public interface CartService {
    /**
     * 获取购物车列表
     * @param userId
     * @return
     */
    List<CartVO> list(Long userId);
}
