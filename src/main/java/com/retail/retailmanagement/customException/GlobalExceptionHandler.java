package com.retail.retailmanagement.customException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.retail.retailmanagement.dto.response.ApiResponse;
import com.retail.retailmanagement.exception.ErrorDetail;
import com.retail.retailmanagement.helper.ResponseUtil;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserExists(UserAlreadyExistsException ex) {
        ResponseEntity<?> response = ResponseUtil.error(HttpStatus.BAD_REQUEST, "Username aready exist");
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(
            MethodArgumentNotValidException ex) {

        List<ErrorDetail> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new ErrorDetail(
                        err.getField(),
                        err.getDefaultMessage(),
                        err.getCode()))
                .toList();

        ApiResponse<?> response = new ApiResponse<>(
                false,
                "Validation failed",
                null,
                errors);

        return ResponseEntity.badRequest().body(response);
    }

}
