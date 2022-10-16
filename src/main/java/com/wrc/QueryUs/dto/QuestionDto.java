package com.wrc.QueryUs.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionDto {
    private int id;
    private int upVotes;
    private Date date;
    private String question;
    private int userId;
    private List<AnswerDto> answers;
}
