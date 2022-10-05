package com.wrc.QueryUs.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private UserDto user;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

}
