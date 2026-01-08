package com.budget_book.budget_book.controller;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인 (명세서 기준 GET 방식)
    @GetMapping
    public ResponseEntity<UserResponseDTO> login(@RequestParam String email, @RequestParam String password) {
        // 실제 로그인 로직은 Service에서 처리
        return ResponseEntity.ok(userService.login(email, password));
    }
}