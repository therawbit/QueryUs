package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.dto.ApiResponse;
import com.wrc.QueryUs.dto.QuestionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/question")
public class QuestionController {
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> postQuestion(@RequestParam(name = "question_text") String questionText){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info(auth.getName());


        return new ResponseEntity<>(new ApiResponse(auth.getName(),true),HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateQuestion(@RequestBody QuestionDto questionDto){
        return null;
    }
    @GetMapping("/question/{id}")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable int id){
        return null;
    }
    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDto>> getAllQuestions(){
        return null;
    }

}
