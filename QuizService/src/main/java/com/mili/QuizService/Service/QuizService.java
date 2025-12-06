package com.mili.QuizService.Service;


import com.mili.QuizService.Feign.QuizInterFace;
import com.mili.QuizService.Repository.QuizRepo;
import com.mili.QuizService.RequestDTO.QuizRequestDTO;
import com.mili.QuizService.RequestDTO.QuizUserRequestDTO;
import com.mili.QuizService.ResponseDTO.QuizResponseDTO;
import com.mili.QuizService.ResponseDTO.QuizResultDTO;
import com.mili.QuizService.entity.Question;
import com.mili.QuizService.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {
//@Autowired
//QuestionRepo questionRepo;
@Autowired
QuizRepo quizRepo;
@Autowired
    QuizInterFace quizInterFace;

public ResponseEntity<String> createQuiz(@RequestBody QuizRequestDTO quizRequestDTO ) {
    String topic = quizRequestDTO.getTopic();
    Integer numberOfQuestions = quizRequestDTO.getNumberOfQuestions();
   List<Long> questionIds = quizInterFace.getQuestionsForQuiz(topic, String.valueOf(numberOfQuestions)).getBody();

//    List<Question> questionList =  questionRepo.findRandomQuestionByTopic(topic,numberOfQuestions);
  Quiz quiz = new Quiz();
   quiz.setQuizName(quizRequestDTO.getTitle());
   quiz.setQuestionIds(questionIds);
    quizRepo.save(quiz);


    return new ResponseEntity<>("Success",HttpStatus.CREATED);
}

    public ResponseEntity<List<QuizResponseDTO>> getQuizQuestions(Long id) {
   Optional<Quiz> quiz =   quizRepo.findById(id);
   List<Long> questionList = quiz.get().getQuestionIds();


   ResponseEntity<List<QuizResponseDTO>> questions = quizInterFace.getQuestionsFromId(questionList);


        return questions;
    }

    public QuizResultDTO submitQuiz(QuizUserRequestDTO dto) {
      Long quizId = dto.getQuizId();
      List <Long> questionList = quizRepo.findById(quizId).get().getQuestionIds();

        ResponseEntity<List<QuizResponseDTO>> response = quizInterFace.getQuestionsFromId(questionList);
        List<QuizResponseDTO> questions = response.getBody();

        Map<Long, String> correctMap = questions.stream()
                .collect(Collectors.toMap(QuizResponseDTO::getId, QuizResponseDTO::get));


        int correct = 0;
        List<QuestionEvaluationDTO> evaluations = new ArrayList<>();

        for (UserAnswerDTO userAnswer : dto.getAnswers()) {

            Long qId = userAnswer.getQuestionId();
            String userSelected = userAnswer.getSelectedOption();
            String correctAnswer = correctMap.get(qId);

            boolean isCorrect = userSelected.equalsIgnoreCase(correctAnswer);

            if (isCorrect) correct++;

            QuestionEvaluationDTO eval = new QuestionEvaluationDTO();
            eval.setQuestionId(qId);
            eval.setUserAnswer(userSelected);
            eval.setCorrectAnswer(correctAnswer);
            eval.setCorrect(isCorrect);

            evaluations.add(eval);
        }

        QuizResultDTO result = new QuizResultDTO();
        result.setTotalQuestions(questionList.size());
        result.setCorrectAnswers(correct);
        result.setWrongAnswers(questionList.size() - correct);
        result.setScore((correct * 100.0) / questionList.size());
        result.setEvaluations(evaluations);

        return result;

    }
}
