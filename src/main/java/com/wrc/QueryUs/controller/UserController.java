package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.dto.ApiResponse;
import com.wrc.QueryUs.dto.RegisterDto;
import com.wrc.QueryUs.dto.UserDto;
import com.wrc.QueryUs.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody RegisterDto registerDto, Errors errors) {

        if(errors.getAllErrors().size()>0){
            return new ResponseEntity<>(new ApiResponse(errors.getAllErrors().get(0).getDefaultMessage(),false),HttpStatus.BAD_REQUEST);
        }
        UserDto u;
        log.info(registerDto.toString());
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            return new ResponseEntity<>(new ApiResponse("Password do not match.", false), HttpStatus.BAD_REQUEST);
        }
        try {
            u =  userService.registerUser(registerDto);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse("User created successfully.", true), HttpStatus.OK);

    }
    @GetMapping
    public String home(){
        return "Welcome to Query Us.";
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable int id){
        UserDto u = userService.getUser(id);
        if(u==null)
            return new ResponseEntity<>(new ApiResponse("User not Found",false),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ApiResponse("Found",true),HttpStatus.FOUND);
    }

}
