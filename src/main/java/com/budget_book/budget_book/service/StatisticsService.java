package com.budget_book.budget_book.service;

import com.budget_book.budget_book.dto.response.StatisticsResponse;
import com.budget_book.budget_book.entity.Transaction;
import com.budget_book.budget_book.entity.Type;
import com.budget_book.budget_book.entity.User;
import com.budget_book.budget_book.repository.TransactionRepository;
import com.budget_book.budget_book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticsService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public StatisticsResponse getMonthlyStatistics(String nickname, int year, int month) {
        // 1. 유저 조회 (닉네임 기준)
        User user = userRepository.findByName(nickname)
                .orElseThrow(() -> new IllegalArgumentException("해당 닉네임의 사용자를 찾을 수 없습니다: " + nickname));

        // 2. 조회할 월의 시작일과 종료일 계산
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);       // 1일
        LocalDate endDate = yearMonth.atEndOfMonth();   // 말일

        // 3. 해당 기간의 모든 내역 조회
        List<Transaction> transactions = transactionRepository.findAllByUserAndDateBetween(user, startDate, endDate);

        // 4. 총 수입 계산 (Type.INCOME)
        BigInteger totalIncome = transactions.stream()
                .filter(t -> t.getType() == Type.INCOME)
                .map(Transaction::getAmount)
                .reduce(BigInteger.ZERO, BigInteger::add);

        // 5. 총 지출 계산 (Type.EXPENSE)
        BigInteger totalExpense = transactions.stream()
                .filter(t -> t.getType() == Type.OUTCOME)
                .map(Transaction::getAmount)
                .reduce(BigInteger.ZERO, BigInteger::add);

        // 6. 카테고리별 지출 통계 계산
        // (지출 내역만 필터링 -> 카테고리 이름으로 그룹핑 -> 금액 합계 계산)
        Map<String, BigInteger> categoryMap = transactions.stream()
                .filter(t -> t.getType() == Type.OUTCOME)
                .collect(Collectors.groupingBy(
                        t -> t.getCategory().getName(),
                        Collectors.reducing(BigInteger.ZERO, Transaction::getAmount, BigInteger::add)
                ));

        // 7. Map -> DTO 리스트 변환
        List<StatisticsResponse.CategoryStat> categoryStats = categoryMap.entrySet().stream()
                .map(entry -> StatisticsResponse.CategoryStat.builder()
                        .categoryName(entry.getKey())
                        .amount(entry.getValue())
                        .build())
                .toList();

        // 8. 최종 응답 생성
        return StatisticsResponse.builder()
                .year(year)
                .month(month)
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .remainingBudget(totalIncome.subtract(totalExpense)) // 남은 예산 = 수입 - 지출
                .categoryStats(categoryStats)
                .build();
    }
}