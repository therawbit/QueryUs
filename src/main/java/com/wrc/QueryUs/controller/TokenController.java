package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.dto.ApiResponse;
import com.wrc.QueryUs.service.TokenService;
import com.wrc.QueryUs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@AllArgsConstructor
public class TokenController {
    private final TokenService tokenService;
    private final UserService userService;


    @GetMapping("/verify")
    public ResponseEntity<ApiResponse> verifyUser(@RequestParam("token") String token){
        if(token.isEmpty()){
            return new ResponseEntity<>(new ApiResponse("Invalid Token",false), HttpStatus.BAD_REQUEST);

        }
        tokenService.confirmToken(token);
        return new ResponseEntity<>(new ApiResponse("Email Verified",true),HttpStatus.OK);

    }
    @PostMapping("/resend")
    public ResponseEntity<ApiResponse> resendToken(@RequestParam String email){
        userService.resentToken(email);
        return new ResponseEntity<>(new ApiResponse("Verification Token Sent",true),HttpStatus.OK);
    }
}
