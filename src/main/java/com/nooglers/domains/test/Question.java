package com.nooglers.domains.test;

import com.nooglers.domains.Card;
import com.nooglers.enums.QuizType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity( name = "question" )
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Question {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column( nullable = false )
    private String definition;

//    @Column( nullable = false, name = "correct_answers" )
//    private String correctAnswers; // separated by the DELIM


//    @Column( nullable = false, name = "wrong_answers" )
//    private String wrongAnswers; // split by the DELIM;

    @Column( name = "user_answer" )
    private String userAnswer;

    @Enumerated( EnumType.STRING )
    @Column( name = "quiz_type" )
    private QuizType quizType;

    @ManyToOne( fetch = FetchType.LAZY )
    private Card card;

    @ManyToOne( fetch = FetchType.LAZY )
    private QuizHistory quizHistory;


    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    List<Variant> variants;

    @Column( columnDefinition = "bool default false", name = "is_correct" )
    private boolean isCorrect;


//    @PrimaryKeyJoinColumn( name = "created_by" )
//    @OneToOne
//    private User createdBy;


//    @PrimaryKeyJoinColumn( name = "updated_by" )
//    @OneToOne
//    private User updatedBy;

//    @Column( name = "correct_answer_count", columnDefinition = "smallint default 0" )
//    private int correctAnswerCount;

}
