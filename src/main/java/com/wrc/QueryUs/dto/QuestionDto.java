package com.wrc.QueryUs.dto;

import com.wrc.QueryUs.entity.Duplicate;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
}
