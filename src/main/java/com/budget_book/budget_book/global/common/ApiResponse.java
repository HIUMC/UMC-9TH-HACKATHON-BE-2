package com.budget_book.budget_book.global.common;

import com.budget_book.budget_book.dto.response.TransactionResDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    private final String code;

    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL) // 데이터가 null일 경우 JSON 결과에서 제외
    private final T result;

    // 성공 시 응답 생성 메서드
    public static <T> ApiResponse<T> onSuccess(T result, TransactionResDTO.PostTransactionDTO postTransactionDTO) {
        return new ApiResponse<>(true, "COMMON200", "요청에 성공하였습니다.", result);
    }

    // 실패 시 응답 생성 메서드 (GlobalExceptionHandler에서 사용)
    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }
}