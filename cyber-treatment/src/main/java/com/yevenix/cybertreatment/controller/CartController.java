package com.yevenix.cybertreatment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yevenix.cybertreatment.common.Result;
import com.yevenix.cybertreatment.service.CartService;
import com.yevenix.cybertreatment.utils.JwtUtil;
import com.yevenix.cybertreatment.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取购物车列表
     * GET /api/cart/list
     * @return
     */
    // 获取购物车列表
    @GetMapping("/list")
    public Result<List<CartVO>> list(@RequestHeader("token") String token) {
        Long userId = jwtUtil.getUserId(token);
        List<CartVO> list = cartService.list(userId);
        return Result.success(list);
    }
    // 添加药品到购物车
    // 更新购物车药品数量
    // 删除购物车药品
    // 清空购物车
    // 获取购物车药品数量
}
