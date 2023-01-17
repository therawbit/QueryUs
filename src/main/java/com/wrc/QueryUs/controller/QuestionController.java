package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.dto.AddQuestionDto;
import com.wrc.QueryUs.dto.UpdateQuestionDto;
import com.wrc.QueryUs.dto.ApiResponse;
import com.wrc.QueryUs.dto.QuestionDto;
import com.wrc.QueryUs.repository.QuestionRepository;
import com.wrc.QueryUs.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> postQuestion(@Valid @RequestBody AddQuestionDto dto,Errors errors) {
        if (errors.getAllErrors().size() > 0) {
            return new ResponseEntity<>(new ApiResponse(errors.getAllErrors().get(0).getDefaultMessage(), false), HttpStatus.BAD_REQUEST);
        }
        log.info(dto.getTags().toString());
        questionService.addQuestion(dto);

        return new ResponseEntity<>(new ApiResponse("Question Posted Successfully.", true), HttpStatus.CREATED);
    }

    @PutMapping("/update")
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
    public ResponseEntity<List<QuestionDto>> searchQuestion(@RequestParam(value = "question") String question,@RequestParam(defaultValue = "0") int page){
        log.info(String.valueOf(page));
        List<QuestionDto> questions;
        questions = questionService.searchQuestion(question,page);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteQuestion(@PathVariable int id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(new ApiResponse("Question Deleted Successfully.",true),HttpStatus.OK);
    }
    @GetMapping("/searchByTag")
    public ResponseEntity<?> searchByTags(@RequestParam("tags")String [] tags,@RequestParam(value = "page",defaultValue = "0") int page){
        log.info(Arrays.toString(tags));
       return new ResponseEntity<>(questionService.searchByTags(tags,page),HttpStatus.OK);

    }
    @PostMapping("/duplicate/{id}")
    public ResponseEntity<ApiResponse> markQuestionDuplicate(@PathVariable("id") int id,@RequestParam("link") String link){
        if(link.trim().isEmpty()){
            return new ResponseEntity<>(new ApiResponse("No link provided",false),HttpStatus.BAD_REQUEST);
        }
        questionService.markDuplicate(id,link);
        return new ResponseEntity<>(new ApiResponse("Question Marked as Duplicate",true),HttpStatus.OK);
    }


}
