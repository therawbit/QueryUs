package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.jwt.JWTService;
import com.wrc.QueryUs.dto.ApiResponse;
import com.wrc.QueryUs.dto.LoginDto;
import com.wrc.QueryUs.dto.RegisterDto;
import com.wrc.QueryUs.dto.UserDto;
import com.wrc.QueryUs.entity.UserRole;
import com.wrc.QueryUs.service.TokenService;
import com.wrc.QueryUs.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody RegisterDto registerDto, Errors errors) {

        if (errors.getAllErrors().size() > 0) {
            return new ResponseEntity<>(new ApiResponse(errors.getAllErrors().get(0).getDefaultMessage(), false), HttpStatus.BAD_REQUEST);
        }
        log.info(registerDto.toString());
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            return new ResponseEntity<>(new ApiResponse("Password do not match.", false), HttpStatus.BAD_REQUEST);
        }
        registerDto.setUserRole(getRoleFromEmail(registerDto.getEmail()));

        userService.registerUser(registerDto);
        return new ResponseEntity<>(new ApiResponse("User created successfully.", true), HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()));
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(new ApiResponse(jwtService.generateToken(dto.getUsername()), true), HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>(new ApiResponse("Logged In Successfully",true), HttpStatus.OK);
    }
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(){
        UserDto u = userService.getCurrentUser();
        return new ResponseEntity<>(u, HttpStatus.OK);

    }
    @GetMapping("/info/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        UserDto u = userService.getUser(id);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    private UserRole getRoleFromEmail(String email) {
        String stRegex = "(...\\d\\d\\d){2}@...\\.edu\\.np";
        String tcRegex = ".*@...\\.edu\\.np";
        if (email.matches(stRegex)) {
            return UserRole.STUDENT;
        } else if (email.matches(tcRegex)) {
            return UserRole.TEACHER;
        } else {
            throw new RuntimeException("Not a Valid IOE email.");
        }


    }


}
