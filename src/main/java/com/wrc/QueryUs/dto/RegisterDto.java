package com.wrc.QueryUs.dto;

import com.wrc.QueryUs.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    private String confirmPassword;
    private UserRole userRole;

}
