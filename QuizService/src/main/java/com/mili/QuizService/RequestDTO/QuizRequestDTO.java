package com.mili.QuizService.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizRequestDTO {
    private String topic;
    private String title;
   private Integer numberOfQuestions;
}
