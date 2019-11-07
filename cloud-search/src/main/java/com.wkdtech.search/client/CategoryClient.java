package com.wkdtech.search.client;

import com.wkdtech.item.api.CategoryApi;
import com.wkdtech.item.entity.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *  访问品牌的feign
 */
@FeignClient(name = "cloud-item-service")
public interface CategoryClient extends CategoryApi {
}
