package com.retail.retailmanagement.exception;

import lombok.Value;

@Value
public class ErrorDetail {

    private String field;
    private String message;
    private String code;

    public ErrorDetail(String field, String message, String code) {
        this.field = field;
        this.message = message;
        this.code = code;
    }
}
