package com.budget_book.budget_book.exception.code;

import com.budget_book.budget_book.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TransactionErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
