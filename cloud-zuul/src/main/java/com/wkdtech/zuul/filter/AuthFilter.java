package com.wkdtech.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wkdtech.auth.utils.JwtUtils;
import com.wkdtech.common.utils.CookieUtils;
import com.wkdtech.zuul.properties.FilterProperties;
import com.wkdtech.zuul.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class AuthFilter extends ZuulFilter{

    @Autowired
    private JwtProperties properties;

    @Autowired
    private FilterProperties filterProperties;


    @Override
    public String filterType() {
        // 选择前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 过滤器顺序
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        // 白名单直接方行
        return !isAllowPath(request.getRequestURI());
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest request = ctx.getRequest();

        // request中获取cookie
        String token = CookieUtils.getCookieValue(request, properties.getCookieName());

        // 解析token
        try {
            //从Token获取解析用户信息
            JwtUtils.getUserInfo(properties.getPublicKey(), token);
            //解析成功，什么都不做，放行

            //todo 如果做权限管理的话，在这做权限检验
        } catch (Exception e) {
            //检验出现异常，返回403
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(403);
            log.error("非法访问，未登录，地址：{}", request.getRemoteHost(), e);
        }
        return null;
    }

    /**
     * 判断请求URI是不是白名单中的URI
     *
     * @param requestURI
     * @return
     */
    private Boolean isAllowPath(String requestURI) {
        boolean flag = false;

        for (String allowPath : filterProperties.getAllowPaths()) {
            if (requestURI.startsWith(allowPath)) {
                //允许
                flag = true;
                break;
            }
        }
        return flag;
    }
}
