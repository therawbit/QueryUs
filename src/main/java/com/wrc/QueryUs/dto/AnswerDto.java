package com.wrc.QueryUs.dto;

import lombok.Data;

@Data
public class AnswerDto {
    private int id;
    private String answer;
    private int questionId;
    private int userId;
    private int upVotes;
}
