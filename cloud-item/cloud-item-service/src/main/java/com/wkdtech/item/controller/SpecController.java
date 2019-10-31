package com.wkdtech.item.controller;

import com.wkdtech.item.entity.SpecParam;
import com.wkdtech.item.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liuyuan on 2019/10/30.
 */
@RestController
@RequestMapping("spec")
public class SpecController {

    @Autowired
    private SpecService specService;

    /**
     * 查询商品规格参数
     *
     * @param gid       规格组ID
     * @param cid       商品分类ID
     * @param searching 是否是搜索字段
     * @param generic   是否是通用字段
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querySpecParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching,
            @RequestParam(value = "generic", required = false) Boolean generic
    ) {
        return ResponseEntity.ok(specService.querySpecParams(gid, cid, searching, generic));
    }
}
