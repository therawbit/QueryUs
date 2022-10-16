package com.wrc.QueryUs.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Answer> answers;


}
