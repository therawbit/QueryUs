package com.wrc.QueryUs.service;

import com.wrc.QueryUs.dto.QuestionDto;
import com.wrc.QueryUs.dto.UpdateQuestionDto;
import com.wrc.QueryUs.entity.Question;
import com.wrc.QueryUs.repository.QuestionRepository;
import com.wrc.QueryUs.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class QuestionService {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    private final AnswerService answerService;


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
    public void updateQuestion(UpdateQuestionDto dto){
        Question q = questionRepository.findById(dto.getId()).orElseThrow(()->new RuntimeException("Question Not Found"));
        if(q.getUser().getId()==dto.getUserId()){
            q.setQuestionText(dto.getQuestion());
            questionRepository.save(q);
        }else{
            throw new RuntimeException("Unauthorized");
        }

    }
    public List<QuestionDto> getAllQuestions(){
        return questionRepository.findAll().stream().map(d->entityToDto(d)).collect(Collectors.toList());
    }
    public QuestionDto entityToDto(Question q){
        QuestionDto dto = new QuestionDto();
        dto.setQuestion(q.getQuestionText());
        dto.setId(q.getId());
        dto.setDate(q.getDate());
        dto.setUpVotes(q.getUpVotes());
        dto.setAnswers(q.getAnswers().stream().map(a->answerService.entityToDto(a)).collect(Collectors.toList()));
        dto.setUserId(q.getUser().getId());
        return dto;
    }
}
