package com.wkdtech.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wkdtech.common.enums.ExceptionEnum;
import com.wkdtech.common.exception.BizException;
import com.wkdtech.common.vo.PageResult;
import com.wkdtech.item.entity.*;
import com.wkdtech.item.mapper.*;
import com.wkdtech.item.service.GoodsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liuyuan on 2019/10/30.
 */
@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable) {
        //分页
        PageHelper.startPage(page, rows);

        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%" + key + "%");
        }
        if (saleable != null) {
            criteria.orEqualTo("saleable", saleable);
        }
        //默认以上一次更新时间排序
        example.setOrderByClause("last_update_time desc");

        //只查询未删除的商品
        criteria.andEqualTo("valid", 1);

        //查询
        List<Spu> spuList = spuMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(spuList)) {
            throw new BizException(ExceptionEnum.SPU_NOT_FOUND);
        }
        //对查询结果中的分类名和品牌名进行处理
        handleCategoryAndBrand(spuList);

        PageInfo<Spu> pageInfo = new PageInfo<>(spuList);

        return new PageResult<>(pageInfo.getTotal(), spuList);
    }

    @Transactional
    @Override
    public void addGoods(Spu spu) {
        //添加商品要添加四个表 spu, spuDetail, sku, stock四张表
        spu.setSaleable(true);
        spu.setValid(true);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        //插入数据
        int count = spuMapper.insert(spu);
        if (count != 1) {
            throw new BizException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
        //插入spuDetail数据
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        count = spuDetailMapper.insert(spuDetail);
        if (count != 1) {
            throw new BizException(ExceptionEnum.GOODS_SAVE_ERROR);
        }

        //插入sku和库存
        saveSkuAndStock(spu);

        //发送消息
        //sendMessage(spu.getId(), "insert");

    }

    @Override
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(spuId);
        if (spuDetail == null) {
            throw new BizException(ExceptionEnum.SPU_NOT_FOUND);
        }
        return spuDetail;
    }

    @Override
    public List<Sku> querySkuBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(sku);
        if (CollectionUtils.isEmpty(skuList)) {
            throw new BizException(ExceptionEnum.SKU_NOT_FOUND);
        }

        //查询库存
        for (Sku sku1 : skuList) {
            sku1.setStock(stockMapper.selectByPrimaryKey(sku1.getId()).getStock());
        }
        return skuList;
    }

    @Transactional
    @Override
    public void updateGoods(Spu spu) {
        if (spu.getId() == 0) {
            throw new BizException(ExceptionEnum.INVALID_PARAM);
        }
        //首先查询sku
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        List<Sku> skuList = skuMapper.select(sku);
        if (!CollectionUtils.isEmpty(skuList)) {
            //删除所有sku
            skuMapper.delete(sku);
            //删除库存
            List<Long> ids = skuList.stream()
                    .map(Sku::getId)
                    .collect(Collectors.toList());
            stockMapper.deleteByIdList(ids);
        }
        //更新数据库  spu  spuDetail
        spu.setLastUpdateTime(new Date());
        //更新spu spuDetail
        int count = spuMapper.updateByPrimaryKeySelective(spu);
        if (count == 0) {
            throw new BizException(ExceptionEnum.GOODS_UPDATE_ERROR);
        }


        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        count = spuDetailMapper.updateByPrimaryKeySelective(spuDetail);
        if (count == 0) {
            throw new BizException(ExceptionEnum.GOODS_UPDATE_ERROR);
        }

        //更新sku和stock
        saveSkuAndStock(spu);

        //发送消息
        //sendMessage(spu.getId(), "update");
    }

    /**
     * 处理商品分类名和品牌名
     *
     * @param spuList
     */
    private void handleCategoryAndBrand(List<Spu> spuList) {
        for (Spu spu : spuList) {
            //根据spu中的分类ids查询分类名
            List<String> nameList =
                    categoryMapper.selectByIdList(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream()
                    .map(Category::getName)
                    .collect(Collectors.toList());
            //对分类名进行处理
            spu.setCname(StringUtils.join(nameList, "/"));

            //查询品牌
            spu.setBname(brandMapper.selectByPrimaryKey(spu.getBrandId()).getName());
        }
    }

    /**
     * 保存sku和库存
     *
     * @param spu
     */
    private void saveSkuAndStock(Spu spu) {
        List<Sku> skuList = spu.getSkus();
        List<Stock> stocks = new ArrayList<>();

        for (Sku sku : skuList) {
            sku.setSpuId(spu.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            int count = skuMapper.insert(sku);
            if (count != 1) {
                throw new BizException(ExceptionEnum.GOODS_SAVE_ERROR);
            }

            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stocks.add(stock);
        }
        //批量插入库存数据
        int count = stockMapper.insertList(stocks);
        if (count == 0) {
            throw new BizException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
    }
}
