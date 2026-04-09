package com.yevenix.cybertreatment.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yevenix.cybertreatment.entity.Cart;
import com.yevenix.cybertreatment.entity.Medicine;
import com.yevenix.cybertreatment.mapper.CartMapper;
import com.yevenix.cybertreatment.mapper.MedicineMapper;
import com.yevenix.cybertreatment.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private MedicineMapper medicineMapper;

    /**
     * 获取购物车列表
     * @param userId
     * @return
     */
    @Override
    public List<CartVO> list(Long userId) {
        // 查询用户的购物车列表
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId, userId)
                .orderByDesc(Cart::getCreateTime);
        List<Cart> carts = cartMapper.selectList(wrapper);
        // 转换为VO并填充药品信息
        List<CartVO> result = new ArrayList<>();
        for (Cart cart : carts) {
            Medicine medicine = medicineMapper.selectById(cart.getMedicineId());
            if (medicine != null && medicine.getStatus() == 1) {
                CartVO cartVO = new CartVO();
                cartVO.setId(cart.getId());
                cartVO.setUserId(cart.getUserId());
                cartVO.setMedicineId(cart.getMedicineId());
                cartVO.setMedicineName(medicine.getName());
                cartVO.setPrice(medicine.getPrice());
                cartVO.setQuantity(cart.getQuantity());
                cartVO.setTotalPrice(medicine.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
                cartVO.setStock(medicine.getStock());

                result.add(cartVO);
            } else {
                // 删除无效（药品不存在或已下架）的购物车商品项记录
                cartMapper.deleteById(cart);
            }
        }
        return result;
    }
}
