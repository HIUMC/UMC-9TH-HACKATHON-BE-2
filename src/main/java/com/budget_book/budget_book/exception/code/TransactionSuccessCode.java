package com.budget_book.budget_book.exception.code;

import com.budget_book.budget_book.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TransactionSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "TRANSACTION200_1",
            "성공적으로 저장했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


}
