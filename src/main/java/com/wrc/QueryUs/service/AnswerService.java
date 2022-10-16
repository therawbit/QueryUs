package com.wrc.QueryUs.service;

import com.wrc.QueryUs.entity.Answer;
import com.wrc.QueryUs.entity.User;
import com.wrc.QueryUs.repository.AnswerRepository;
import com.wrc.QueryUs.repository.QuestionRepository;
import com.wrc.QueryUs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    public void addAnswer(int questionId,String answer){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Answer ans = new Answer();
        ans.setAnswer(answer);
        ans.setQuestion(questionRepository.findById(questionId).orElseThrow());
        ans.setUser(userRepository.getByEmail(email).orElseThrow());
        ans.setUpVotes(0);
        answerRepository.save(ans);
    }
}
