package com.budget_book.budget_book.service;

import com.budget_book.budget_book.dto.request.CategoryCreateRequest;
import com.budget_book.budget_book.dto.response.CategoryResponse;
import com.budget_book.budget_book.entity.Category;
import com.budget_book.budget_book.entity.User;
import com.budget_book.budget_book.repository.CategoryRepository;
import com.budget_book.budget_book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    // 1. 카테고리 생성 (생성은 여전히 User 객체가 필요하므로 조회 유지)
    @Transactional
    public void createCategory(String nickname, CategoryCreateRequest request) {
        User user = userRepository.findByName(nickname)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + nickname));

        Category category = new Category(
                user,
                request.getName(),
                request.getType()
        );

        categoryRepository.save(category);
    }

    // 2. 카테고리 조회
    public List<CategoryResponse> getCategories(String nickname) {
        // User 조회 없이 바로 닉네임으로 카테고리 목록을 가져옵니다.
        return categoryRepository.findAllByUser_Name(nickname).stream()
                .map(CategoryResponse::from)
                .toList();
    }

    // 3. 카테고리 삭제
    @Transactional
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}