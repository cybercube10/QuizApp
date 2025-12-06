package com.mili.questionService.controller;

import com.mili.questionService.Question;
import com.mili.questionService.RequestDTO.QuestionWrapper;
import com.mili.questionService.RequestDTO.QuizUserRequestDTO;
import com.mili.questionService.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/getAllQuestions")
        public ResponseEntity<List<Question>> questions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/topic/{topic}")
    public ResponseEntity<List<Question>> topic(@PathVariable String topic) {
     return questionService.getQuestionsByTopic(topic);
    }

    @PostMapping("/add")
    public String addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    //generate questions in a quiz
   @GetMapping("/generate")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String topic, @RequestParam String numOfQuestions) {
        return questionService.getQuestionsForQuiz(topic, numOfQuestions);
   }
    //get Questions
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Long> ids) {
   return questionService.getQuestionsFromIds(ids);
    }
    //getScore

    @PostMapping("/score")
    public ResponseEntity<Integer> getScore(@RequestBody QuizUserRequestDTO quizUserRequestDTO) {
     return questionService.getScore(quizUserRequestDTO);
    }
}
