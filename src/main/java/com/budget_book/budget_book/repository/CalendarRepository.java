package com.budget_book.budget_book.repository;

import com.budget_book.budget_book.entity.Transaction;
import com.budget_book.budget_book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Transaction, Long> {

    // 특정 날짜의 내역 조회
    List<Transaction> findByUserAndDate(User user, LocalDate date);

    // 특정 기간(한 달) 동안의 내역 조회
    List<Transaction> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}