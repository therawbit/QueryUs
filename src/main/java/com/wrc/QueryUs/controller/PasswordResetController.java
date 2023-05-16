package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.dto.ApiResponse;
import com.wrc.QueryUs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reset-password")
@AllArgsConstructor
public class PasswordResetController {
    private final UserService userService;
    @PostMapping("/token")
    public ResponseEntity<ApiResponse> sendToken(@RequestParam String email){
        userService.resetPasswordToken(email);
        return new ResponseEntity<>(new ApiResponse("Password Reset Token has been sent.",true), HttpStatus.OK);
    }


}
