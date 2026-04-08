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
     * 搜索药品
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    Page<Medicine> search(String keyword, Integer page, Integer size);

    /**
     * 获取药品详情
     * @return
     */
    Medicine detail();
}
