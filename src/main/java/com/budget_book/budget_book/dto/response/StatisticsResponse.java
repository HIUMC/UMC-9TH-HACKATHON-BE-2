package com.budget_book.budget_book.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Builder
public class StatisticsResponse {
    private Integer year;
    private Integer month;
    private BigInteger totalIncome;      // 총 수입
    private BigInteger totalExpense;     // 총 지출
    private BigInteger remainingBudget;  // 남은 예산 (총 수입 - 총 지출)
    private List<CategoryStat> categoryStats; // 카테고리별 지출 상세

    @Getter
    @Builder
    public static class CategoryStat {
        private String categoryName; // 카테고리 이름 (예: 식비)
        private BigInteger amount;   // 해당 카테고리 총 지출액
    }
}