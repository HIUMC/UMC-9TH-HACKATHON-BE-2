package com.budget_book.budget_book.repository;

import com.budget_book.budget_book.entity.Transaction;
import com.budget_book.budget_book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // 특정 유저의 시작일(start) ~ 종료일(end) 사이의 모든 내역 조회
    List<Transaction> findAllByUserAndDateBetween(User user, LocalDate start, LocalDate end);
}

