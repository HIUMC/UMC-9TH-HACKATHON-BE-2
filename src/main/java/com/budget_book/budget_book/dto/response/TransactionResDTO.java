package com.budget_book.budget_book.dto.response;

import com.budget_book.budget_book.entity.Type;
import lombok.Builder;

import java.math.BigInteger;
import java.time.LocalDate;

public class TransactionResDTO {

    @Builder
    public record DeleteAllDTO(
            Long deletedCount
    ){}

    @Builder
    public record PostTransactionDTO(
            BigInteger amount,
            LocalDate date,
            String memo,
            String imgUrl,
            Type type
    ){}
}
