package com.country.countryside.common;

import com.country.countryside.exception.DescribeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 对所有异常进行捕捉
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResult handler(Exception e){
        return BaseResult.fail(e.getMessage());
    }

    /**
     * 捕获自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = DescribeException.class)
    public BaseResult handler(DescribeException e){
        return BaseResult.fail(e.getCode(), e.getMessage(),null);
    }
}
