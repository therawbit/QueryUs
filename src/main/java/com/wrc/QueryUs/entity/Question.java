package com.wrc.QueryUs.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @CreationTimestamp
    private Date date;
    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Answer> answers;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> upVotedUsers;


}
