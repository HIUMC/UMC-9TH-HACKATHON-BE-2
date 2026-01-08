package com.budget_book.budget_book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 조회
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    // 카테고리 추가
    @PostMapping
    public ResponseEntity<Void> addCategory(@RequestBody CategoryRequestDTO request) {
        categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 카테고리 삭제
    @DeleteMapping("/{category_id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("category_id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}