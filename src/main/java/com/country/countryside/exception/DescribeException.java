package com.country.countryside.exception;

import lombok.Data;

@Data
public class DescribeException extends RuntimeException{

    private String code;

    public DescribeException(String code, String message) {
        super(message);
        this.code = code;
    }
}
