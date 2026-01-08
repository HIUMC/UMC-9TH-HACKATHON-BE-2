package com.budget_book.budget_book.controller;

import com.budget_book.budget_book.dto.response.CalendarResponseDTO;
import com.budget_book.budget_book.entity.User;
import com.budget_book.budget_book.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    // 캘린더 - 월 호출
    @GetMapping("/month")
    public ResponseEntity<List<CalendarResponseDTO.MonthResponse>> getMonthCalendar(
            @RequestParam int year,
            @RequestParam int month
            /* @AuthenticationPrincipal User user (로그인 유저 정보) */) {

        // 임시 유저 (실제 구현 시 인증된 유저 객체 사용)
        User user = null;

        List<CalendarResponseDTO.MonthResponse> response = calendarService.getMonthCall(user, year, month);
        return ResponseEntity.ok(response);
    }

    // 캘린더 - 일 호출
    @GetMapping("/day")
    public ResponseEntity<List<CalendarResponseDTO.DayResponse>> getDayCalendar(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
            /* @AuthenticationPrincipal User user */) {

        User user = null;

        List<CalendarResponseDTO.DayResponse> response = calendarService.getDayCall(user, date);
        return ResponseEntity.ok(response);
    }
}