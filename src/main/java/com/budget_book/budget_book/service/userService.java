package com.budget_book.budget_book.service;

import com.budget_book.budget_book.dto.request.UserRequestDTO;
import com.budget_book.budget_book.entity.User;
import com.budget_book.budget_book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class userService {

    private final UserRepository userRepository;

    @Transactional
    public User loginOrJoin(UserRequestDTO.LoginDTO request) {
        // 1. 닉네임으로 사용자 조회
        return userRepository.findByNickname(request.getNickname())
                .map(user -> {
                    // 2. 사용자가 있다면 비밀번호 비교
                    if (!user.getPassword().equals(request.getPassword())) {
                        // 비밀번호가 다르면 예외 발생 (에러 코드는 적절히 설정)
                        throw new RuntimeException("비밀번호가 일치하지 않습니다.");
                    }
                    return user; // 로그인 성공
                })
                .orElseGet(() -> {
                    // 3. 사용자가 없다면 회원가입 진행
                    User newUser = User.builder()
                            .name(request.getNickname())
                            .password(request.getPassword())
                            .build();
                    return userRepository.save(newUser);
                });
    }
}