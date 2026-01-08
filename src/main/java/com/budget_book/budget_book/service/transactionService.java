package com.budget_book.budget_book.service;

import com.budget_book.budget_book.converter.TransactionConverter;
import com.budget_book.budget_book.dto.request.TransactionReqDTO;
import com.budget_book.budget_book.dto.response.TransactionResDTO;
import com.budget_book.budget_book.entity.Category;
import com.budget_book.budget_book.entity.Transaction;
import com.budget_book.budget_book.entity.Type;
import com.budget_book.budget_book.entity.User;
import com.budget_book.budget_book.repository.CategoryRepository;
import com.budget_book.budget_book.repository.TransactionRepository;
import com.budget_book.budget_book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class transactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    //수입
    public Transaction addIncoming(TransactionReqDTO.PostTransactionDTO dto){
        return save(dto, Type.INCOME);
    }

    //지출
    public Transaction addOutcoming(TransactionReqDTO.PostTransactionDTO dto){
        return save(dto, Type.OUTCOME);
    }

    private Transaction save(TransactionReqDTO.PostTransactionDTO dto, Type type) {
        User user = userRepository.findByName(dto.name())
                .orElseThrow(() -> new IllegalArgumentException("유저 없음: " + dto.name()));
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("카테고리 없음: " + dto.categoryId()));

        Transaction tx = TransactionConverter.toTransaction(dto, user, category, type);
        return transactionRepository.save(tx);
    }

}
