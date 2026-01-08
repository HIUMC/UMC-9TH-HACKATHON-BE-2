package com.budget_book.budget_book.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "CATEGORIES")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    // 실제로는 User 엔티티와 @ManyToOne 관계 해줘야함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 20, nullable = false)
    private String  name;

    @Enumerated(EnumType.STRING) // Enum 이름을 문자열로 DB에 저장 (권장)
    @Column(length = 10, nullable = false)
    private CategoryType type;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    // 생성자 (Builder 패턴을 사용하면 더 좋습니다)
    public Category(User userId, String name, CategoryType type) {
        this.user = userId;
        this.name = name;
        this.type = type;
    }
}
