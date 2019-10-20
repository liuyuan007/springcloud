package com.wkdtech.common.vo;

import com.wkdtech.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * 异常通用返回
 */
@Data
public class ExceptionResult {

    private int code;

    private String msg;

    private long timestamp;

    public ExceptionResult(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
