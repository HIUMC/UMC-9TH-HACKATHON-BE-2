package com.budget_book.budget_book.dto.request;

import lombok.Getter;

public class UserRequestDTO {

    @Getter
    public static class LoginDTO {
        private String name;
        private String password;
    }
}
