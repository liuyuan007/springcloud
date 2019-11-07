package com.wkdtech.item.service;

import com.wkdtech.item.entity.Category;

import java.util.List;

/**
 * Created by liuyuan on 2019/10/26.
 */
public interface CategoryService {

    List<Category> queryCategoryLisByPid(Long pid);

    List<Category> queryCategoryByIds(List<Long> ids);
}
