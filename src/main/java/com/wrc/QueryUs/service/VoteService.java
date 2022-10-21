package com.wrc.QueryUs.service;

import com.wrc.QueryUs.entity.Answer;
import com.wrc.QueryUs.entity.Question;
import com.wrc.QueryUs.entity.User;
import com.wrc.QueryUs.repository.AnswerRepository;
import com.wrc.QueryUs.repository.QuestionRepository;
import com.wrc.QueryUs.security.QueryUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class VoteService {
    public final QuestionRepository questionRepository;
    public final AnswerRepository answerRepository;
    public final QueryUtils queryUtils;

    public String voteQuestion(int id) {
        User votingUser = queryUtils.getCurrentLoggedInUser();
        Question votedQuestion = questionRepository.findById(id).orElseThrow();
        int questionVotes = votedQuestion.getUpVotes();
        int questionPostingUsersReputation = votedQuestion.getUser().getReputation();
        if (votedQuestion.getUpVotedUsers().contains(votingUser)) {
            votedQuestion.getUpVotedUsers().remove(votingUser);
            votedQuestion.setUpVotes(questionVotes - 1);
            votedQuestion.getUser().setReputation(questionPostingUsersReputation - 1);
            questionRepository.save(votedQuestion);
            return "Down Voted";
        }
        votedQuestion.getUpVotedUsers().add(votingUser);
        votedQuestion.setUpVotes(questionVotes + 1);
        votedQuestion.getUser().setReputation(questionPostingUsersReputation + 1);
        questionRepository.save(votedQuestion);
        return "Up Voted";


    }

    public String voteAnswer(int id) {
        User votingUser = queryUtils.getCurrentLoggedInUser();
        Answer votedAnswer = answerRepository.findById(id).orElseThrow();
        int answerUpVotes = votedAnswer.getUpVotes();
        int answerPostingUsersReputation = votedAnswer.getUser().getReputation();
        if (votedAnswer.getUpVotedUsers().contains(votingUser)) {
            votedAnswer.getUpVotedUsers().remove(votingUser);
            votedAnswer.setUpVotes(answerUpVotes - 1);
            votedAnswer.getUser().setReputation(answerPostingUsersReputation - 1);
            answerRepository.save(votedAnswer);
            return "Down Voted";
        }
        votedAnswer.getUpVotedUsers().add(votingUser);
        votedAnswer.setUpVotes(answerUpVotes + 1);
        votedAnswer.getUser().setReputation(answerPostingUsersReputation + 1);
        answerRepository.save(votedAnswer);
        return "Up Voted";
    }
}
