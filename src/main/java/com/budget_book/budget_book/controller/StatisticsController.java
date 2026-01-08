package com.budget_book.budget_book.controller;

import com.budget_book.budget_book.dto.response.StatisticsResponse;
import com.budget_book.budget_book.global.apiPayload.ApiResponse;
import com.budget_book.budget_book.global.apiPayload.code.GeneralSuccessCode;
import com.budget_book.budget_book.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
@Tag(name = "Statistics API", description = "월별 통계 조회 API")
public class StatisticsController {

    private final StatisticsService statisticsService;

    /**
     * 월별 통계 조회
     * 요청 예시: GET /api/statistics?nickname=사용자&year=2026&month=1
     */
    @GetMapping
    @Operation(summary = "월별 통계 조회", description = "년, 월을 입력받아 해당 월의 수입/지출 합계 및 카테고리별 지출 내역을 반환합니다.")
    public ApiResponse<StatisticsResponse> getMonthlyStatistics(
            @RequestParam String nickname,
            @RequestParam int year,
            @RequestParam int month
    ) {
        StatisticsResponse result = statisticsService.getMonthlyStatistics(nickname, year, month);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}