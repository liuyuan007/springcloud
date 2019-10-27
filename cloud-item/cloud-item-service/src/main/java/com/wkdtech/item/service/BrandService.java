package com.wkdtech.item.service;

import com.wkdtech.common.vo.PageResult;
import com.wkdtech.item.entity.Brand;

/**
 * Created by liuyuan on 2019/10/26.
 */
public interface BrandService {

    PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key);
}
