package com.wrc.QueryUs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class UpdateQuestionDto {
    private int id;
    @NotBlank(message = "Question Text Cannot be Empty")
    private String question;
    private int userId;
}
