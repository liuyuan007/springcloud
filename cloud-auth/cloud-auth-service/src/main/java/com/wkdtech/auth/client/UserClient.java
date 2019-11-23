package com.wkdtech.auth.client;

import com.wkdtech.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author bystander
 * @date 2018/10/1
 */
@FeignClient(value = "cloud-user")
public interface UserClient extends UserApi {
}
