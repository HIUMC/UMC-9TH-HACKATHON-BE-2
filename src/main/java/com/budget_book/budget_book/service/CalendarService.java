package com.budget_book.budget_book.service;

import com.budget_book.budget_book.dto.response.CalendarResponseDTO;
import com.budget_book.budget_book.entity.Transaction;
import com.budget_book.budget_book.entity.Type;
import com.budget_book.budget_book.entity.User;
import com.budget_book.budget_book.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarService {

    private final CalendarRepository calendarRepository;

    /**
     * 일 호출: 특정 날짜의 상세 내역 리스트 반환
     */
    public List<CalendarResponseDTO.DayResponse> getDayCall(User user, LocalDate date) {
        return calendarRepository.findByUserAndDate(user, date).stream()
                .map(t -> CalendarResponseDTO.DayResponse.builder()
                        .transactionId(t.getId())
                        .categoryName(t.getCategory().getName())
                        .amount(t.getAmount())
                        .type(t.getType())
                        .date(t.getDate())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 월 호출: 해당 월의 날짜별 수입/지출 합산 반환
     */
    public List<CalendarResponseDTO.MonthResponse> getMonthCall(User user, int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        // 1. 해당 유저의 한 달 치 거래 내역 조회
        List<Transaction> transactions = calendarRepository.findByUserAndDateBetween(user, startDate, endDate);

        // 2. 날짜별로 그룹화
        Map<LocalDate, List<Transaction>> groupedByDate = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getDate));

        // 3. 그룹화된 데이터를 기반으로 날짜별 수입/지출 합산 및 DTO 변환
        return groupedByDate.entrySet().stream()
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<Transaction> dayTransactions = entry.getValue();

                    // 수입 합계 계산
                    BigInteger incomeSum = dayTransactions.stream()
                            .filter(t -> t.getType() == Type.INCOME)
                            .map(Transaction::getAmount)
                            .reduce(BigInteger.ZERO, BigInteger::add);

                    // 지출 합계 계산
                    BigInteger expenseSum = dayTransactions.stream()
                            .filter(t -> t.getType() == Type.OUTCOME)
                            .map(Transaction::getAmount)
                            .reduce(BigInteger.ZERO, BigInteger::add);

                    // 수정된 부분: 내부 클래스인 MonthResponse를 명확히 호출
                    return CalendarResponseDTO.MonthResponse.builder()
                            .date(date)
                            .dailyTotalIncome(incomeSum)
                            .dailyTotalExpense(expenseSum)
                            .build();
                })
                .sorted(Comparator.comparing(CalendarResponseDTO.MonthResponse::getDate)) // 날짜순 정렬 추가
                .collect(Collectors.toList());
    }
}