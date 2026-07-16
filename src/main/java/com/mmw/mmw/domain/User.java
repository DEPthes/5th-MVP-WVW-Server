package com.mmw.mmw.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Email(message="올바른 이메일 형시이 아닙니다.")
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="user_tier")
    private UserTier userTier = UserTier.FREE;

    @Column(name="current_points")
    private Integer currentPoints=0;

    @Column(name = "free_chats_left")
    private Integer freeChatsLeft=3;

    @Column(name="created_at")
    private LocalDateTime createAt;

    //INSERT되기 전에 실행하여 엔티티 생성시 현재시간을 주입
    @PrePersist
    protected void onCreate(){
        this.createAt=LocalDateTime.now();
    }

}
