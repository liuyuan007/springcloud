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
    GOODS_NOT_FOUND(404,"商品没有找到！"),
    BRAND_CREATE_FAILED(500,"品牌新增失败！"),
    GOODS_UPDATE_ERROR(500,"商品更新失败！"),
    INVALID_FILE_FORMAT(500,"无效的文件格式！"),
    INVALID_PARAM(500,"无效的参数！"),
    SPU_NOT_FOUND(404,"未找到的SPU！"),
    SPEC_PARAM_NOT_FOUND(404,"SPEC_PARAM未找到！"),
    SKU_NOT_FOUND(404,"SKU未找到！"),
    UPLOAD_IMAGE_EXCEPTION(500,"上传文件失败！"),
    GOODS_SAVE_ERROR(500,"商品保存失败！"),
    CATEGORY_NOT_FOUND(404,"类别没有找到！"),
    STOCK_NOT_ENOUGH(500,"库存不足"),
    USER_NOT_EXIST(500,"用户不存在"),
    PASSWORD_NOT_MATCHING(500,"密码不正确"),
    USERNAME_OR_PASSWORD_ERROR(500,"用户名或密码不正确");
    private int code;
    private String msg;
}
