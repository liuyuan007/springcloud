package com.wkdtech.item.service;

import com.wkdtech.common.vo.PageResult;
import com.wkdtech.item.dto.CartDto;
import com.wkdtech.item.entity.Sku;
import com.wkdtech.item.entity.Spu;
import com.wkdtech.item.entity.SpuDetail;

import java.util.List;

/**
 * Created by liuyuan on 2019/10/30.
 */
public interface GoodsService {

    PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable);

    void addGoods(Spu spu);

    SpuDetail querySpuDetailBySpuId(Long spuId);

    List<Sku> querySkuBySpuId(Long id);

    void updateGoods(Spu spu);

    Spu querySpuBySpuId(Long spuId);

    void decreaseStock(List<CartDto> cartDtos);
}
