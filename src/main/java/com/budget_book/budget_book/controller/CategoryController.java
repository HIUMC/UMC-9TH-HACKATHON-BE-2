package com.budget_book.budget_book.controller;

import com.budget_book.budget_book.dto.request.CategoryCreateRequest;
import com.budget_book.budget_book.dto.response.CategoryResponse;
import com.budget_book.budget_book.global.common.ApiResponse;
import com.budget_book.budget_book.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Category API", description = "카테고리 관련 API (Security X, 닉네임 사용)")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 1. 카테고리 목록 조회
     * 요청: GET /api/categories?nickname=사용자닉네임
     */
    @GetMapping
    @Operation(summary = "카테고리 목록 조회", description = "특정 유저의 카테고리 목록을 조회합니다.")
    public ApiResponse<List<CategoryResponse>> getCategories(@RequestParam String nickname) {
        return ApiResponse.onSuccess(categoryService.getCategories(nickname));
    }

    /**
     * 2. 카테고리 생성
     * 요청: POST /api/categories?nickname=사용자닉네임
     * Body: { "name": "식비", "type": "EXPENSE" }
     */
    @PostMapping
    @Operation(summary = "카테고리 생성", description = "특정 유저의 새로운 카테고리를 생성합니다.")
    public ApiResponse<Void> createCategory(
            @RequestParam String nickname,
            @RequestBody CategoryCreateRequest request
    ) {
        categoryService.createCategory(nickname, request);
        return ApiResponse.onSuccess(null);
    }

    /**
     * 3. 카테고리 삭제
     * 요청: DELETE /api/categories/{categoryId}
     */
    @DeleteMapping("/{categoryId}")
    @Operation(summary = "카테고리 삭제", description = "카테고리 ID를 이용하여 삭제합니다.")
    public ApiResponse<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ApiResponse.onSuccess(null);
    }
}