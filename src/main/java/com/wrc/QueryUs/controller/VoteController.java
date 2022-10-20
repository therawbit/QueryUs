package com.wrc.QueryUs.controller;

import com.wrc.QueryUs.dto.ApiResponse;
import com.wrc.QueryUs.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
@AllArgsConstructor
public class VoteController {

    public final VoteService voteService;

    @PostMapping("/question/{id}")
    public ResponseEntity<ApiResponse> voteQuestion(@PathVariable int id){
        String response = voteService.voteQuestion(id);
        return new ResponseEntity<>(new ApiResponse(response,true), HttpStatus.OK);
    }

}
