package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.dto.ApiResponse;
import com.wrc.QueryUs.dto.RegisterDto;
import com.wrc.QueryUs.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterDto registerDto) {
        log.info(registerDto.toString());
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            return new ResponseEntity<>(new ApiResponse("Password do not match.", false), HttpStatus.BAD_REQUEST);
        }
        try {
            userService.registerUser(registerDto);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse("User created successfully.", true), HttpStatus.OK);

    }


}
