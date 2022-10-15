package com.wrc.QueryUs.service;

import com.wrc.QueryUs.dto.QuestionDto;
import com.wrc.QueryUs.entity.Question;
import com.wrc.QueryUs.handler.QueryUsHandler;
import com.wrc.QueryUs.repository.QuestionRepository;
import com.wrc.QueryUs.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
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
    public QuestionDto getQuestion(int id){
        Question q = questionRepository.findById(id).orElseThrow(()->new RuntimeException("Question Not Found."));
        return entityToDto(q);


    }
    private QuestionDto entityToDto(Question q){
        QuestionDto dto = new QuestionDto();
        dto.setQuestionText(q.getQuestionText());
        dto.setId(q.getId());
        dto.setDate(q.getDate());
        dto.setUpVotes(q.getUpVotes());
        dto.setUserId(q.getUser().getId());
        return dto;
    }
}
