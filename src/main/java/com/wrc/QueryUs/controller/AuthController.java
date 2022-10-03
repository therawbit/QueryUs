package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.dto.RegisterDto;
import com.wrc.QueryUs.dto.UserDto;
import com.wrc.QueryUs.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterDto registerDto){
        log.info(registerDto.toString());
        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            return new ResponseEntity<>("Password do not match",HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(authService.registerUser(registerDto), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    



}
