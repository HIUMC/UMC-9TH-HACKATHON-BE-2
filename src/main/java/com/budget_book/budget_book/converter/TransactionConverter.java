package com.budget_book.budget_book.converter;

import com.budget_book.budget_book.dto.request.TransactionReqDTO;
import com.budget_book.budget_book.dto.response.TransactionResDTO;
import com.budget_book.budget_book.entity.Category;
import com.budget_book.budget_book.entity.Transaction;
import com.budget_book.budget_book.entity.Type;
import com.budget_book.budget_book.entity.User;

public class TransactionConverter {

    //Transaction DTO를 entity로
    public static Transaction toTransaction(
            TransactionReqDTO.PostTransactionDTO dto,
            User user,
            Category category,
            Type type
    ){
        return Transaction.builder()
                .amount(dto.amount())
                .date(dto.date())
                .memo(dto.memo())
                .imgUrl(dto.imgUrl())
                .category(category)
                .user(user)
                .type(type)
                .build();
    }

    public static TransactionResDTO.PostTransactionDTO toDTO (
            Transaction transaction
    ){
        return new TransactionResDTO.PostTransactionDTO(
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getMemo(),
                transaction.getImgUrl(),
                transaction.getType()
        );
    }


}
