package com.yevenix.cybertreatment.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yevenix.cybertreatment.entity.Medicine;
import com.yevenix.cybertreatment.mapper.MedicineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl implements MedicineService{
    @Autowired
    private MedicineMapper medicineMapper;
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

    @Override
    public Page<Medicine> search(String keyword, Integer page, Integer size) {
        return null;
    }

    @Override
    public Medicine detail() {
        return null;
    }
}
