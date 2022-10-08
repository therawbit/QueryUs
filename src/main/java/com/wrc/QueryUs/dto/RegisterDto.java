package com.wrc.QueryUs.dto;

import com.wrc.QueryUs.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    @NotBlank(message = "First name cannot be blank.")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank.")
    private String lastName;
    private String middleName;
    @Email(message = "Provide valid email.")
    private String email;

    private String password;
    private String confirmPassword;
    @NotNull(message = "NO user role defined.")
    private UserRole userRole;

}
