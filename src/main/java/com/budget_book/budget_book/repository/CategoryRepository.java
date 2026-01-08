package com.budget_book.budget_book.repository;

import com.budget_book.budget_book.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUser_Name(String name);
}

