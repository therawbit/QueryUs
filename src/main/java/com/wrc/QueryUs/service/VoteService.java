package com.wrc.QueryUs.service;

import com.wrc.QueryUs.entity.Question;
import com.wrc.QueryUs.entity.User;
import com.wrc.QueryUs.repository.AnswerRepository;
import com.wrc.QueryUs.repository.QuestionRepository;
import com.wrc.QueryUs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class VoteService {
    public final QuestionRepository questionRepository;
    public final AnswerRepository answerRepository;
    public final UserRepository userRepository;

    public String voteQuestion(int id){
        User votingUser = userRepository.
                getByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow();
        Question votedQuestion = questionRepository.findById(id).orElseThrow();
        int questionVotes = votedQuestion.getUpVotes();
        int questionPostingUsersReputation = votedQuestion.getUser().getReputation();
        if( votedQuestion.getUpVotedUsers().contains(votingUser)){
            votedQuestion.getUpVotedUsers().remove(votingUser);
            votedQuestion.setUpVotes(questionVotes-1);
            votedQuestion.getUser().setReputation(questionPostingUsersReputation-1);
            questionRepository.save(votedQuestion);
            return "Down Voted";
        }
        votedQuestion.getUpVotedUsers().add(votingUser);
        votedQuestion.setUpVotes(questionVotes+1);
        votedQuestion.getUser().setReputation(questionPostingUsersReputation+1);
        questionRepository.save(votedQuestion);
        return "Up Voted";


    }
}
