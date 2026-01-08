package com.budget_book.budget_book.dto.response;

import com.budget_book.budget_book.entity.Category;
import com.budget_book.budget_book.entity.CategoryType;
import com.budget_book.budget_book.entity.Type;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {
    private Long categoryId;   // 엔티티의 필드명에 맞춤
    private String name;
    private Type type;

    // Entity -> DTO 변환 메서드
    public static CategoryResponse from(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId()) // getter 이름 주의
                .name(category.getName())
                .type(category.getType())
                .build();
    }
}