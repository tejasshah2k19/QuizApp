package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "quiz_questions")
@Getter
@Setter
public class QuizQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizQuestionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizId", nullable = false)
    private QuizEntity quiz;

    @Column(nullable = false)
    private String question;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String correctAns;

}
