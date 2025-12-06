package com.mili.QuizService.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponseDTO {
    private Long id;



    private String questionTitle;



    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
