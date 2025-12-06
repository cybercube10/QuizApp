package com.mili.QuizService.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultDTO {
    private int totalQuestions;
    private int correctAnswers;
    private int wrongAnswers;
    private double score;
    private List<QuestionEvaluationDTO> evaluations;
}