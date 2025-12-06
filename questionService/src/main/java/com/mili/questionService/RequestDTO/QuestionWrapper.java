package com.mili.questionService.RequestDTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
    private Long id;

    private String difficulty;

    private String topic;

    private String questionTitle;


    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
