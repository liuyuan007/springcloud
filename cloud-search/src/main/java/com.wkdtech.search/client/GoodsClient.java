package com.wkdtech.search.client;

import com.wkdtech.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by liuyuan on 2019/11/5.
 */
@FeignClient("cloud-item-service")
public interface GoodsClient extends GoodsApi {



}
