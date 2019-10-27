package com.wkdtech.item.controller;

import com.wkdtech.common.vo.PageResult;
import com.wkdtech.item.entity.Brand;
import com.wkdtech.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyuan on 2019/10/26.
 */
@RestController("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key) {
        return ResponseEntity.ok(brandService.queryBrandByPageAndSort(page, rows, sortBy, desc, key));
    }
}
