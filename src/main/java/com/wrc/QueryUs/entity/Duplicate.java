package com.wrc.QueryUs.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "duplicate")
@Getter
@Setter
@RequiredArgsConstructor
public class Duplicate {
    public Duplicate(int questionId, User user) {
        this.questionId = questionId;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int questionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
