package com.budget_book.budget_book.dto.request;

import com.budget_book.budget_book.entity.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateRequest {
    private String name;       // 카테고리명 (예: 식비)
    private CategoryType type; // 수입/지출 (INCOME, EXPENSE)
}
