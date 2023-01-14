package com.wrc.QueryUs.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column(name = "question_title")
    private String questionTitle;
    @Column(name = "question_text")
    private String questionText;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Answer> answers;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<User> upVotedUsers;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<QuestionTag> questionTags;


}
