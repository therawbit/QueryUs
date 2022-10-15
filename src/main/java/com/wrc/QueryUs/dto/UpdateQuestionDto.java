package com.wrc.QueryUs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class UpdateQuestionDto {
    @NotBlank
    private int id;
    @NotEmpty
    private String questionText;
    @NotBlank
    private int userId;
}
