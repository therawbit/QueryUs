package com.wrc.QueryUs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id @GeneratedValue
    private int id;

    private String answer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private Question question;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int upVotes;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> upVotedUsers;
}
