package com.wrc.QueryUs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id @GeneratedValue
    private int id;

    private String answer;
    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private Question question;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int upVotes;
    private Date date;
}
