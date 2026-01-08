package com.budget_book.budget_book.global.common;

import com.budget_book.budget_book.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;

    public GeneralException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}