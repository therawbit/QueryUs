package com.wrc.QueryUs.dto;

import com.wrc.QueryUs.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;

    private String email;

    private UserRole role;
    private int reputation;

}
