package com.retail.retailmanagement.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.retail.retailmanagement.dto.response.ApiResponse;

public class ResponseUtil {
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {

        ApiResponse<T> response = new ApiResponse<T>(true, message, data, null);

        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message) {

        ApiResponse<T> response = new ApiResponse<T>(false, message, null, null);

        return ResponseEntity.status(status).body(response);
    }
}
