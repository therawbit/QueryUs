package com.wrc.QueryUs.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionDto {
    private int id;
    private int upVotes;
    private Date date;
    private String questionText;
    private int userId;
}
