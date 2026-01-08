package com.budget_book.budget_book.global.error;

import com.budget_book.budget_book.global.common.ApiResponse;
import com.budget_book.budget_book.global.common.code.BaseErrorCode;
import com.budget_book.budget_book.global.error.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 모든 RestController에서 발생하는 예외를 가로챕니다.
public class GlobalExceptionHandler {

    // 1. 우리가 직접 정의한 GeneralException(또는 그 자식들)을 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(GeneralException ex) {
        BaseErrorCode code = ex.getCode();
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(ApiResponse.onFailure(code.getCode(), code.getMessage(), null));
    }

    // 2. 그 외에 예상치 못한 모든 예외(Exception)를 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleAllException(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(ApiResponse.onFailure("COMMON500", "서버 에러가 발생했습니다.", ex.getMessage()));
    }
}