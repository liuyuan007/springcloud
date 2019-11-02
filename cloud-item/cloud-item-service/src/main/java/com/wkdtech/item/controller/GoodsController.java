package com.wkdtech.item.controller;

import com.wkdtech.common.vo.PageResult;
import com.wkdtech.item.entity.Sku;
import com.wkdtech.item.entity.Spu;
import com.wkdtech.item.entity.SpuDetail;
import com.wkdtech.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liuyuan on 2019/10/30.
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "saleable", required = false)Boolean saleable) {
        return ResponseEntity.ok(goodsService.querySpuByPage(page,rows,key,saleable));
    }

    /**
     * 添加商品
     * @param spu
     * @return
     */
    @PostMapping("goods")
    public ResponseEntity<Void> addGoods(@RequestBody Spu spu) {
        goodsService.addGoods(spu);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 更新商品(注意是put请求)
     * @param spu
     * @return
     */
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody Spu spu) {
        goodsService.updateGoods(spu);
        return ResponseEntity.ok().build();
    }


    /**
     * 查询spu详情
     * @param spuId
     * @return
     */
    @GetMapping("spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId") Long spuId) {
        return ResponseEntity.ok(goodsService.querySpuDetailBySpuId(spuId));
    }

    /**
     * 根据spuId查询商品详情
     * @param id
     * @return
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id") Long id) {
        return ResponseEntity.ok(goodsService.querySkuBySpuId(id));

    }

}
