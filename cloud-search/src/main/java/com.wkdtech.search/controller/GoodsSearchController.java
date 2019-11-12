package com.wkdtech.search.controller;

import com.wkdtech.search.entity.Goods;
import com.wkdtech.search.entity.SearchRequest;
import com.wkdtech.search.entity.SearchResult;
import com.wkdtech.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bystander
 * @date 2018/9/22
 */
@RestController
public class GoodsSearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("page")
    public ResponseEntity<SearchResult<Goods>> search(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(searchService.search(searchRequest));
    }
}
