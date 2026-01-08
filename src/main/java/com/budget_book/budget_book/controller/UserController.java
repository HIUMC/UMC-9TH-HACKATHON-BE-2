package com.budget_book.budget_book.controller;

import com.budget_book.budget_book.dto.request.UserRequestDTO;
import com.budget_book.budget_book.dto.response.UserResponseDTO;
import com.budget_book.budget_book.entity.User;
import com.budget_book.budget_book.global.apiPayload.ApiResponse;
import com.budget_book.budget_book.global.apiPayload.code.GeneralSuccessCode;
import com.budget_book.budget_book.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final userService userService;

    @PostMapping("/login")
    public ApiResponse<UserResponseDTO.LoginResultDTO> login(@RequestBody UserRequestDTO.LoginDTO request) {
        // 서비스 호출
        User user = userService.loginOrJoin(request);

        // Entity를 Response DTO로 변환
        UserResponseDTO.LoginResultDTO result = UserResponseDTO.LoginResultDTO.builder()
                .id(user.getId())
                .nickname(user.getName())
                .createdAt(user.getCreatedAt())
                .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}