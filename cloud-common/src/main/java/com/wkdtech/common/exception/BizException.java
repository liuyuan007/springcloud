package com.wkdtech.common.exception;

import com.wkdtech.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BizException extends RuntimeException{

    private ExceptionEnum exceptionEnum;
}
