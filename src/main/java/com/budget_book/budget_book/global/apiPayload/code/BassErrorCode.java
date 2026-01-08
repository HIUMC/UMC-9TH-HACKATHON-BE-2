package com.budget_book.budget_book.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BassErrorCode {
    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}
