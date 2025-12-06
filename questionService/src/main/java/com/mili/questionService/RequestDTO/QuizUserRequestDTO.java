package com.mili.questionService.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizUserRequestDTO {
    private Long quizId;
    private List<UserAnswerDTO> answers;
}

