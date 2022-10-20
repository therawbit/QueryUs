package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.dto.UpdateQuestionDto;
import com.wrc.QueryUs.dto.ApiResponse;
import com.wrc.QueryUs.dto.QuestionDto;
import com.wrc.QueryUs.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> postQuestion(@RequestParam(name = "question_text") String questionText) {
        if (questionText.trim().isEmpty()) {
            return new ResponseEntity<>(new ApiResponse("Question cannot be empty", false), HttpStatus.BAD_REQUEST);
        }
        questionService.addQuestion(questionText);

        return new ResponseEntity<>(new ApiResponse("Question Posted Successfully.", true), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateQuestion(@Valid @RequestBody UpdateQuestionDto dto, Errors errors) {
        if (errors.getAllErrors().size() > 0) {
            return new ResponseEntity<>(new ApiResponse(errors.getAllErrors().get(0).getDefaultMessage(), false), HttpStatus.BAD_REQUEST);
        }
        questionService.updateQuestion(dto);
        return new ResponseEntity<>(new ApiResponse("Updated Successfully",true),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable int id) {
        return new ResponseEntity<>(questionService.getQuestion(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestionDto>> getAllQuestions(@RequestParam(defaultValue = "0") int pageNo) {
        List<QuestionDto> questions = questionService.getAllQuestions(pageNo);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<QuestionDto>> searchQuestion(@RequestParam String question,@RequestParam(defaultValue = "0") int page){
        log.info(String.valueOf(page));
        List<QuestionDto> questions = questionService.searchQuestion(question,page);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

}
