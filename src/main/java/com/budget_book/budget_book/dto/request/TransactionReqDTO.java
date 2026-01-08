package com.budget_book.budget_book.dto.request;

import com.budget_book.budget_book.entity.Category;
import com.budget_book.budget_book.entity.Type;
import com.budget_book.budget_book.entity.User;

import java.math.BigInteger;
import java.time.LocalDate;
public class TransactionReqDTO {

    public record PostTransactionDTO(
            BigInteger amount,
            LocalDate date,
            Long categoryId,
            String memo,
            String imgUrl,
            String name
    ){}

    public record DeleteAllDTO() {}
}
