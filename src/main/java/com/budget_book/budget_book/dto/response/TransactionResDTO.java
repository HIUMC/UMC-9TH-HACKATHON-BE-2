package com.budget_book.budget_book.dto.response;

import lombok.Builder;

public class TransactionResDTO {

    @Builder
    public record DeleteAllDTO(){}

    @Builder
    public record PostTransactionDTO(){}
}
