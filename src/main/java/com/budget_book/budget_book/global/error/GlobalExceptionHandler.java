package com.budget_book.budget_book.global.error;

import com.budget_book.budget_book.global.apiPayload.ApiResponse;
import com.budget_book.budget_book.global.apiPayload.code.BaseErrorCode;
import com.budget_book.budget_book.global.apiPayload.code.GeneralErrorCode;
import com.budget_book.budget_book.global.common.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. 직접 정의한 GeneralException 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(GeneralException ex) {
        BaseErrorCode code = ex.getCode();
        // ApiResponse.onFailure는 (BaseErrorCode, T) 형태여야 합니다.
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(ApiResponse.onFailure(code, null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleAllException(Exception ex) {
        // GeneralErrorCode를 쓰지 않고 직접 정의
        BaseErrorCode defaultCode = new BaseErrorCode() {
            @Override
            public org.springframework.http.HttpStatus getHttpStatus() {
                return org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR; // 500 에러
            }

            @Override
            public String getCode() {
                return "COMMON500";
            }

            @Override
            public String getMessage() {
                return "서버 에러가 발생했습니다.";
            }
        };

        return ResponseEntity
                .status(defaultCode.getHttpStatus())
                .body(ApiResponse.onFailure(defaultCode, ex.getMessage()));
    }
}