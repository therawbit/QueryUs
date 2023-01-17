package com.wrc.QueryUs.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AnswerDto {
    private int id;
    private String answer;
    private int userId;
    private int voteCount;
    private Date timestamp;
    private boolean isUpVoted;
}
