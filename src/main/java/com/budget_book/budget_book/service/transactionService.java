package com.budget_book.budget_book.service;

import com.budget_book.budget_book.dto.request.TransactionReqDTO;
import com.budget_book.budget_book.dto.response.TransactionResDTO;
import com.budget_book.budget_book.entity.Transaction;
import com.budget_book.budget_book.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class transactionService {

    private final TransactionRepository transactionRepository;

    //수입
    public TransactionResDTO.PostTransactionDTO addIncoming(
            TransactionReqDTO.PostTransactionDTO dto
    ){

    };

    //지출
    public TransactionResDTO.PostTransactionDTO addOutcoming(
            TransactionReqDTO.PostTransactionDTO dto
    ){

    };

}
