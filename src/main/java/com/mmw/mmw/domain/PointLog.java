package com.mmw.mmw.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class PointLog {

    @Id
    @GeneratedValue
    @Column(name="log_id")
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private Integer amount;

    @Column(name="free_usage_change")
    private Integer freeUsageChange = 0;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PointLogType type;


//    @Column(name="session_id")
//    private Long sessionId;

    @Column(name="create_at")
    private LocalDateTime createAt;

    @PrePersist
    protected void onCreate(){
        this.createAt=LocalDateTime.now();
    }
}
