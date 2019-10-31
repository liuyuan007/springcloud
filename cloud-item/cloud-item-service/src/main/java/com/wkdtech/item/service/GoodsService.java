package com.wkdtech.item.service;

import com.wkdtech.common.vo.PageResult;
import com.wkdtech.item.entity.Spu;

/**
 * Created by liuyuan on 2019/10/30.
 */
public interface GoodsService {

    PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable);

    void addGoods(Spu spu);
}
