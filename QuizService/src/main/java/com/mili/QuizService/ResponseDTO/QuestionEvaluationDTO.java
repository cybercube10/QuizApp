package com.mili.QuizService.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEvaluationDTO {
    private Long questionId;
    private String userAnswer;
    private String correctAnswer;
    private boolean isCorrect;
}
