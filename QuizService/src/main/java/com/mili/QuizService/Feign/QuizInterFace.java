package com.mili.QuizService.Feign;

import com.mili.QuizService.RequestDTO.QuizUserRequestDTO;
import com.mili.QuizService.ResponseDTO.QuizResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuizInterFace {
    //generate questions in a quiz
    @GetMapping("/generate")
     ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String topic, @RequestParam String numOfQuestions);
    //get Questions
    @PostMapping("/getQuestions")
    ResponseEntity<List<QuizResponseDTO>> getQuestionsFromId(@RequestBody List<Long> ids);

//getScore

    @PostMapping("/score")
    ResponseEntity<Integer> getScore(@RequestBody QuizUserRequestDTO quizUserRequestDTO);
}

