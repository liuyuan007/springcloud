package com.wkdtech.item.service.impl;

import com.wkdtech.common.enums.ExceptionEnum;
import com.wkdtech.common.exception.BizException;
import com.wkdtech.item.entity.Category;
import com.wkdtech.item.mapper.CategoryMapper;
import com.wkdtech.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by liuyuan on 2019/10/26.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryLisByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categoryList = categoryMapper.select(category);
        if(CollectionUtils.isEmpty(categoryList)) {
            throw new BizException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categoryList;
    }
}
