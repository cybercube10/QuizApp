package com.mili.QuizService.controller;

import com.mili.QuizService.RequestDTO.QuizRequestDTO;
import com.mili.QuizService.RequestDTO.QuizUserRequestDTO;
import com.mili.QuizService.ResponseDTO.QuizResponseDTO;
import com.mili.QuizService.ResponseDTO.QuizResultDTO;
import com.mili.QuizService.Service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;



    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizRequestDTO quizRequestDTO ) {

        return quizService.createQuiz(quizRequestDTO);

    }

   @GetMapping("/get/{id}")
   public ResponseEntity<List<QuizResponseDTO>> getQuiz(@PathVariable String id ) {
       Long quizId = Long.parseLong(id);
       return quizService.getQuizQuestions(quizId);
  }

    @PostMapping("submit")
    public ResponseEntity<QuizResultDTO> submitQuiz(@RequestBody QuizUserRequestDTO quizUserRequestDTO) {
        QuizResultDTO quizResultDTO =  quizService.submitQuiz(quizUserRequestDTO);
        return ResponseEntity.ok(quizResultDTO);
    }


}
