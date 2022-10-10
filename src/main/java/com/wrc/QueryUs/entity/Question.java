package com.wrc.QueryUs.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int upVotes;
    @Column(name = "question_text")
    private String questionText;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Date date;


}
