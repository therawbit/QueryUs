package com.wrc.QueryUs.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionDto {
    private int id;
    private int voteCount;
    private Date timestamp;
    private String questionText;
    private String questionTitle;
    private int userId;
    private List<AnswerDto> answers;
    private boolean isUpVoted;
    private List<String> tags;
    private int answerCount;

    private int originalQuestionId;
    private int dupMarkingUserId;

    private int views;
}
