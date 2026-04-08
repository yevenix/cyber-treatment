package com.yevenix.cybertreatment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yevenix.cybertreatment.entity.Medicine;

public interface MedicineService {
    /**
     * 获取药店的药品列表
     * @param pharmacyId
     * @param page
     * @param size
     * @return
     */
    Page<Medicine> listByPharmacy(Long pharmacyId, Integer page, Integer size);

    /**
     * 根据药品名称模糊搜索药品列表
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    Page<Medicine> searchByName(String keyword, Integer page, Integer size);

    /**
     * 获取单个药品详情
     * @return
     */
    Medicine getById(Long id);
}
