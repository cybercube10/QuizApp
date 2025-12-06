package com.mili.questionService;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "questions", schema = "public")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String difficulty;

    private String topic;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "correct_answer")
    private String correctAnswer;

    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
