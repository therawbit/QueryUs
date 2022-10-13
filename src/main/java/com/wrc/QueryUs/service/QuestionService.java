package com.wrc.QueryUs.service;

import com.wrc.QueryUs.entity.Question;
import com.wrc.QueryUs.repository.QuestionRepository;
import com.wrc.QueryUs.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class QuestionService {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;


    public void addQuestion(String questionText) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Question question = new Question();
        question.setQuestionText(questionText);
        question.setDate(Date.from(Instant.now()));
        question.setUser(userRepository.getByEmail(email).orElseThrow());
        question.setUpVotes(0);
        questionRepository.save(question);


    }
}
