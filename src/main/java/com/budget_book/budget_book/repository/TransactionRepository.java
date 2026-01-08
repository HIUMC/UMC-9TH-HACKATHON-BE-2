package com.budget_book.budget_book.repository;

import com.budget_book.budget_book.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
