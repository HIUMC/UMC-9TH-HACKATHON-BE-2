package com.budget_book.budget_book.exception;

import com.budget_book.budget_book.global.apiPayload.code.BaseErrorCode;
import com.budget_book.budget_book.global.common.GeneralException;
import org.springframework.http.HttpStatus;

public class TransactionException extends GeneralException {
    public TransactionException(BaseErrorCode code) {
        super(code);
    }
}
