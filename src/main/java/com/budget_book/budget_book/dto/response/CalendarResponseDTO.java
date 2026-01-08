package com.budget_book.budget_book.dto.response;

import com.budget_book.budget_book.entity.Type;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;

public class CalendarResponseDTO {

    // 일별 내역 조회를 위한 응답 DTO
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DayResponse {
        private Long transactionId;
        private String categoryName;
        private BigInteger amount;
        private Type type;
        private LocalDate date;
    }

    // 월별 요약 조회를 위한 응답 DTO (달력 칸 표시용)
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MonthResponse {
        private LocalDate date;
        private BigInteger dailyTotalIncome;
        private BigInteger dailyTotalExpense;
    }
}