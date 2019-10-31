package com.wkdtech.item.mapper;

import com.wkdtech.common.mapper.BaseMapper;
import com.wkdtech.item.entity.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author bystander
 * @date 2018/9/18
 */
public interface StockMapper extends BaseMapper<Stock, Long> {

    @Update("update tb_stock set stock = stock - #{num} where sku_id = #{skuId} and stock >= #{num}")
    int decreaseStock(@Param("skuId") Long skuId, @Param("num") Integer num);
}
