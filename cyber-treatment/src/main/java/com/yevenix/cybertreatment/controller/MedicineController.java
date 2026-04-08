package com.yevenix.cybertreatment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yevenix.cybertreatment.common.Result;
import com.yevenix.cybertreatment.entity.Medicine;
import com.yevenix.cybertreatment.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    /**
     * 根据药店查询药品列表（分页）
     * GET /api/medicine/listByPharmacy?pharmacyId=1&page=1&size=10
     * @return
     */
    @GetMapping("/listByPharmacy")
    public Result<Page<Medicine>> listByPharmacy(
            @RequestParam Long pharmacyId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        Page<Medicine> result = medicineService.listByPharmacy(pharmacyId, page, size);
        return Result.success(result);
    }

    /**
     * 搜索药品
     * GET /api/medicine/search?keyword=xxx&page=1&size=10
     * @return
     */
    @GetMapping("/search")
    public Result<Page<Medicine>> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<Medicine> result = medicineService.searchByName(keyword, page, size);
        return Result.success(result);
    }

    /**
     * 查询单个药品详情
     * GET /api/medicine/detail/1
     * @return
     */
    @GetMapping("/detail/{id}")
    public Result<Medicine> detail(@PathVariable Long id) {
        Medicine medicine = medicineService.getById(id);
        return Result.success(medicine);
    }
}
