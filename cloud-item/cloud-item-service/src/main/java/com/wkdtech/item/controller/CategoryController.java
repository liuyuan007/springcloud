package com.wkdtech.item.controller;

import com.wkdtech.item.entity.Category;
import com.wkdtech.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liuyuan on 2019/10/26.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryCategoryLisByPid(@RequestParam("pid") Long pid) {
        return ResponseEntity.ok(categoryService.queryCategoryLisByPid(pid));
    }
}
