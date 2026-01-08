package com.budget_book.budget_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BudgetBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetBookApplication.class, args);
	}

}
