package com.wkdtech.common.advice;

import com.wkdtech.common.enums.ExceptionEnum;
import com.wkdtech.common.exception.BizException;
import com.wkdtech.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 通用异常处理
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(BizException.class)
    public ResponseEntity<ExceptionResult> handException(BizException e) {
        ExceptionEnum exceptionEnum = e.getExceptionEnum();
        return ResponseEntity.status(exceptionEnum.getCode()).body(new ExceptionResult(exceptionEnum));
    }
}
