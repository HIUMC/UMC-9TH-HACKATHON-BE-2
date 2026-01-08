package com.budget_book.budget_book.controller;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // 캘린더 - 월 호출
    @GetMapping("/calender/month")
    public ResponseEntity<List<TransactionResponseDTO>> getMonthlyCalendar(@RequestParam int year, @RequestParam int month) {
        return ResponseEntity.ok(transactionService.getMonthlyData(year, month));
    }

    // 캘린더 - 일 호출
    @GetMapping("/calender/day")
    public ResponseEntity<List<TransactionResponseDTO>> getDailyCalendar(@RequestParam int year, @RequestParam int month, @RequestParam int day) {
        return ResponseEntity.ok(transactionService.getDailyData(year, month, day));
    }

    // 수입 추가 (명세서 경로 /transaction/inome 준수)
    @PostMapping("/transaction/inome")
    public ResponseEntity<Void> addIncome(@RequestBody TransactionRequestDTO request) {
        transactionService.addTransaction(request, "INCOME");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 지출 추가
    @PostMapping("/transaction/outcome")
    public ResponseEntity<Void> addOutcome(@RequestBody TransactionRequestDTO request) {
        transactionService.addTransaction(request, "OUTCOME");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 내역 전체 삭제
    @DeleteMapping("/transaction")
    public ResponseEntity<Void> deleteAllTransactions() {
        transactionService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    // 월별 통계 호출
    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponseDTO> getMonthlyStatistics(@RequestParam int year, @RequestParam int month) {
        return ResponseEntity.ok(transactionService.getMonthlyStatistics(year, month));
    }
}