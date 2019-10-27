package com.wkdtech.item.mapper;

import com.wkdtech.item.entity.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by liuyuan on 2019/10/26.
 */
public interface BrandMapper extends Mapper<Brand>{

    @Insert("insert into tb_category_brand (category_id, brand_id) values (#{cid}, #{bid})")
    int saveCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);
}
