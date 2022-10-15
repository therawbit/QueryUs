package com.wrc.QueryUs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateQuestionDto {
    private int id;
    @NotEmpty(message = "Question Text Cannot be Empty")
    private String question;
    private int userId;
}
