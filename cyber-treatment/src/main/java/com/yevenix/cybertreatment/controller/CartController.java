package com.yevenix.cybertreatment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yevenix.cybertreatment.common.Result;
import com.yevenix.cybertreatment.dto.CartAddDTO;
import com.yevenix.cybertreatment.dto.CartUpdateDTO;
import com.yevenix.cybertreatment.service.CartService;
import com.yevenix.cybertreatment.utils.JwtUtil;
import com.yevenix.cybertreatment.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取购物车列表
     * GET /api/carts/list
     * @return
     */
    @GetMapping("/list")
    public Result<List<CartVO>> list(@RequestHeader("token") String token) {
        Long userId = jwtUtil.getUserId(token);
        List<CartVO> list = cartService.list(userId);
        return Result.success(list);
    }

    /**
     * 添加药品到购物车
     * POST /api/carts/add
     * @return
     */
    @PostMapping("/add")
    public Result<Void> add(@RequestBody CartAddDTO cartAddDTO, @RequestHeader("token") String token) {
        Long userId = jwtUtil.getUserId(token);
        cartService.add(cartAddDTO, userId);
        return Result.success(null);
    }

    /**
     * 更新购物车中某个药品数量
     * PUT /api/carts/updateQuantity
     * @return
     */
    @PutMapping("/updateQuantity")
    public Result<Void> updateQuantity(@RequestBody CartUpdateDTO cartUpdateDTO, @RequestHeader("token") String token) {
        Long userId = jwtUtil.getUserId(token);
        cartService.update(cartUpdateDTO, userId);
        return Result.success("更新成功",null);
    }

    /**
     * 删除购物车中的药品
     * DELETE /api/carts/1
     * @return
     */
    @DeleteMapping("/{cartId}")
    public Result<Void> delete(@PathVariable Long cartId, @RequestHeader("token") String token) {
        Long userId = jwtUtil.getUserId(token);
        cartService.delete(cartId, userId);
        return Result.success("删除成功",null);
    }

    /**
     * 清空购物车
     * DELETE /api/carts
     * @return
     */
    @DeleteMapping
    public Result<Void> clear(@RequestHeader("token") String token) {
        Long userId = jwtUtil.getUserId(token);
        cartService.clear(userId);
        return Result.success("清空成功",null);
    }

    /**
     * 获取购物车药品种类数
     * GET /api/carts/count
     * @return
     */
    @GetMapping("/count")
    public Result<Integer> count(@RequestHeader("token") String token) {
        Long userId = jwtUtil.getUserId(token);
        Integer count = cartService.count(userId);
        return Result.success(count);
    }
}
