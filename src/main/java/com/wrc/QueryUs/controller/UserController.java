package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.dto.UserDto;
import com.wrc.QueryUs.entity.User;
import com.wrc.QueryUs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public UserDto registerUser(@RequestBody User user){
        log.info(user.toString());
        return userService.createUser(user).orElseThrow(RuntimeException::new);

    }
    @GetMapping("/home")
    public String test(){
        return "hi hello ola namaster!!!";
    }

    



}
