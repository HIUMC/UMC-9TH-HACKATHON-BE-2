package com.budget_book.budget_book.controller;

import com.budget_book.budget_book.converter.TransactionConverter;
import com.budget_book.budget_book.dto.request.TransactionReqDTO;
import com.budget_book.budget_book.dto.response.TransactionResDTO;
import com.budget_book.budget_book.entity.Transaction;
import com.budget_book.budget_book.exception.code.TransactionSuccessCode;
import com.budget_book.budget_book.global.apiPayload.ApiResponse;
import com.budget_book.budget_book.service.transactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final transactionService transactionService;

    //수입추가
    @PostMapping("/api/transaction/incoming")
    public ApiResponse<TransactionResDTO.PostTransactionDTO> addIncoming(
            @RequestBody TransactionReqDTO.PostTransactionDTO dto
    ){
        Transaction transaction = transactionService.addIncoming(dto);
        return ApiResponse.onSuccess(
                TransactionSuccessCode.FOUND,
                TransactionConverter.toDTO(transaction)
                );
    }

    //지출추가
    @PostMapping("/api/transaction/outcoming")
    public ApiResponse<TransactionResDTO.PostTransactionDTO> addOutcoming(
            @RequestBody TransactionReqDTO.PostTransactionDTO dto
    ){
        Transaction transaction = transactionService.addIncoming(dto);
        return ApiResponse.onSuccess(
                TransactionSuccessCode.FOUND,
                TransactionConverter.toDTO(transaction)
        );    }

    //전체 삭제
    @DeleteMapping("/api/transaction")
    public ApiResponse<TransactionResDTO.DeleteAllDTO> deleteTransaction(
            @RequestBody TransactionReqDTO.DeleteAllDTO dto
    ){
        return null;
    }
}
