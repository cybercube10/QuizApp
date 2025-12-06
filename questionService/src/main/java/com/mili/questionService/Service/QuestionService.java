package com.mili.questionService.Service;

import com.mili.questionService.Question;
import com.mili.questionService.Repository.QuestionRepo;
import com.mili.questionService.RequestDTO.QuestionWrapper;
import com.mili.questionService.RequestDTO.QuizUserRequestDTO;
import com.mili.questionService.RequestDTO.UserAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo questionRepo;


    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> getQuestionsByTopic(String topic) {
        try{
            return new ResponseEntity<>( questionRepo.findByTopic(topic), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);

    }

    public String addQuestion(Question question) {
        questionRepo.save(question);
        return "Question added successfully";
    }

    public ResponseEntity<List<Long>> getQuestionsForQuiz(String topic, String numOfQuestions) {
        Integer count = Integer.parseInt(numOfQuestions);

        List<Long> questions = questionRepo.findRandomQuestionByTopic(topic,count);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Long> questionIds) {
             List <QuestionWrapper> questions = new ArrayList<>();
 List<Question> question = new ArrayList<>();

 for(Long id: questionIds){
   question.add(questionRepo.findById(id).get());
 }

for(Question q: question){
    QuestionWrapper questionWrapper = new QuestionWrapper();
    questionWrapper.setId(q.getId());
    questionWrapper.setQuestionTitle(q.getQuestionTitle());
    questionWrapper.setOption1(q.getOption1());
    questionWrapper.setOption2(q.getOption2());
    questionWrapper.setOption3(q.getOption3());
    questionWrapper.setOption4(q.getOption4());
    questions.add(questionWrapper);
}

            return  new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(QuizUserRequestDTO quizUserRequestDTO) {
  List<Long> questionIds = quizUserRequestDTO.getAnswers().stream().map(UserAnswerDTO::getQuestionId).toList();
        List<Question> questions = questionRepo.findAllById(questionIds);

        Map<Long, String> correctAnswerMap = questions.stream()
                .collect(Collectors.toMap(Question::getId, Question::getCorrectAnswer));

        int correctCount = 0;

        for (UserAnswerDTO userAnswer : quizUserRequestDTO.getAnswers()) {
            String correctAnswer = correctAnswerMap.get(userAnswer.getQuestionId());
            if (correctAnswer != null && correctAnswer.equalsIgnoreCase(userAnswer.getSelectedOption())) {
                correctCount++;
            }
        }
        return ResponseEntity.ok(correctCount);


    }
}
