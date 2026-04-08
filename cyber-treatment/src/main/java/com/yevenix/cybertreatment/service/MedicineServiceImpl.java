package com.yevenix.cybertreatment.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yevenix.cybertreatment.entity.Medicine;
import com.yevenix.cybertreatment.mapper.MedicineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MedicineServiceImpl implements MedicineService{
    @Autowired
    private MedicineMapper medicineMapper;
    /**
     * 获取药店的药品列表
     * @param pharmacyId
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Medicine> listByPharmacy(Long pharmacyId, Integer page, Integer size) {
        // 构建查询条件
        LambdaQueryWrapper<Medicine> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Medicine::getPharmacyId, pharmacyId)
                .eq(Medicine::getStatus,1)
                .orderByDesc(Medicine::getSales);
        // 分页查询
        return medicineMapper.selectPage(new Page<>(page, size), wrapper);
    }

    /**
     * 根据药品名称模糊搜索药品列表
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Medicine> searchByName(String keyword, Integer page, Integer size) {
        // 构建查询条件
        LambdaQueryWrapper<Medicine> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            // 模糊搜索（LIKE）
            wrapper.like(Medicine::getName, keyword);
        }
        wrapper.eq(Medicine::getStatus, 1)
                .orderByDesc(Medicine::getSales);
        // 分页查询
        return medicineMapper.selectPage(new Page<>(page, size), wrapper);
    }

    /**
     * 获取单个药品详情
     * @return
     */
    @Override
    public Medicine getById(Long id) {
        // 根据药品ID查询药品信息
        Medicine medicine = medicineMapper.selectById(id);
        // 检查药名是否存在
        if (medicine == null) {
            throw new RuntimeException("药品不存在");
        }
        // 返回药品详情
        return medicine;
    }
}
