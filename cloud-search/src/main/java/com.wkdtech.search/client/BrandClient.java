package com.wkdtech.search.client;

import com.wkdtech.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by liuyuan on 2019/11/5.
 */
@FeignClient("cloud-item-service")
public interface BrandClient extends BrandApi{

}
