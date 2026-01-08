package com.budget_book.budget_book.controller;

import com.budget_book.budget_book.dto.request.TransactionReqDTO;
import com.budget_book.budget_book.exception.code.TransactionSuccessCode;
import com.budget_book.budget_book.global.common.ApiResponse;
import com.budget_book.budget_book.service.transactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final transactionService transactionService;

    //수입추가
    @PostMapping("/api/transaction/incoming")
    public ApiResponse<TransactionSuccessCode> addIncoming(
            @RequestBody TransactionReqDTO.PostTransactionDTO dto
    ){
        return ApiResponse.onSuccess(TransactionSuccessCode.FOUND, transactionService.addIncoming(dto));
    }

    //지출추가
    @PostMapping("/api/transaction/outcoming")
    public ApiResponse<TransactionSuccessCode> addOutcoming(
            @RequestBody TransactionReqDTO.PostTransactionDTO dto
    ){
        return ApiResponse.onSuccess(TransactionSuccessCode.FOUND, transactionService.addOutcoming(dto));
    }

}
