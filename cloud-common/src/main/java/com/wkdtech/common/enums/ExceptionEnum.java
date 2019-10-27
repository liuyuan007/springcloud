package com.wkdtech.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常枚举值
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {

    PRICE_CANNOT_BE_NULL(400,"价格不能为空！"),
    BRAND_NOT_FOUND(404,"品牌没有找到！"),
    CATEGORY_NOT_FOUND(404,"类别没有找到！")
    ;
    private int code;
    private String msg;
}
