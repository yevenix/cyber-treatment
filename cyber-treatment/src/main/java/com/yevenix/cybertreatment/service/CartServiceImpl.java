package com.yevenix.cybertreatment.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yevenix.cybertreatment.dto.CartAddDTO;
import com.yevenix.cybertreatment.dto.CartUpdateDTO;
import com.yevenix.cybertreatment.entity.Cart;
import com.yevenix.cybertreatment.entity.Medicine;
import com.yevenix.cybertreatment.mapper.CartMapper;
import com.yevenix.cybertreatment.mapper.MedicineMapper;
import com.yevenix.cybertreatment.vo.CartVO;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
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

    /**
     * 添加药品到购物车
     * @param cartAddDTO
     * @param userId
     */
    @Override
    public void add(CartAddDTO cartAddDTO, Long userId) {
        // 检查该药品是否已在购物车
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId, userId)
                .eq(Cart::getMedicineId, cartAddDTO.getMedicineId());
        Cart existCart = cartMapper.selectOne(wrapper);
        System.out.println(existCart);
        // 检查购物车是否存在该药品
        if (existCart != null) {
            // 存在：更新购物车中该药品数量
            existCart.setQuantity(existCart.getQuantity() + cartAddDTO.getQuantity());
            cartMapper.updateById(existCart);
        }
        else{
            // 不存在：添加该药品到购物车
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setMedicineId(cartAddDTO.getMedicineId());
            cart.setQuantity(cartAddDTO.getQuantity());
            cart.setCreateTime(LocalDateTime.now());
            cart.setUpdateTime(LocalDateTime.now());
            cartMapper.insert(cart);

            log.info("添加成功,购物车:{}", cart);
        }
    }

    /**
     * 更新购物车中某个药品数量
     * @param cartUpdateDTO
     * @param userId
     */
    @Override
    public void update(CartUpdateDTO cartUpdateDTO, Long userId) {
        // 根据购物车ID查询药品记录
        Cart cart = cartMapper.selectById(cartUpdateDTO.getCartId());
        // 检查购物车中有无该药品
        if (cart == null) {
            throw new RuntimeException("购物车中没有该药品");
        }
        // 检查用户是否有权限修改该购物车
        if (!cart.getUserId().equals(userId)) {
            throw new RuntimeException("无权操作");
        }
        // 检查数量是否合法
        if (cartUpdateDTO.getQuantity() < 1) {
            throw new RuntimeException("数量不能小于1");
        }
        if (cartUpdateDTO.getQuantity() > 999) {
            throw new RuntimeException("数量不能大于999");
        }
        // 检查药品库存是否充足
        Medicine medicine = medicineMapper.selectById(cart.getMedicineId());
        if (medicine == null || medicine.getStatus() == 0) {
            throw new RuntimeException("药品已下架");
        }else if (medicine.getStock() < cartUpdateDTO.getQuantity()) {
            throw new RuntimeException("库存不足，当前库存：" + medicine.getStock());
        }
        // 记录购物车中该药品原先的数量
        Integer previousQuantity = cart.getQuantity();
        // 更新数量
        cart.setQuantity(cartUpdateDTO.getQuantity());
        cartMapper.updateById(cart);

        log.info("更新成功,用户:{},购物车ID:{},数量:{} -> {}", userId, cart.getId(), previousQuantity, cart.getQuantity());
    }
}
