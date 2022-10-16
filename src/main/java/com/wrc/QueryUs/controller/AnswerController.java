package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.dto.ApiResponse;
import com.wrc.QueryUs.service.AnswerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    @PostMapping("/add/{id}")
    public ResponseEntity<ApiResponse> addAnswer(@PathVariable int id,@RequestParam String answer){
        if(answer.trim().isEmpty()){
            throw new RuntimeException("Answer cannot be empty.");
        }
        answerService.addAnswer(id,answer);

        return new ResponseEntity<>(new ApiResponse("Answer Posted Successfully.",true), HttpStatus.OK);
    }
}
