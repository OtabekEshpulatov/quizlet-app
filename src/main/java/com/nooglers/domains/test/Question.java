package com.nooglers.domains.test;

import com.nooglers.domains.Card;
import com.nooglers.enums.QuizType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity( name = "question" )
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column( nullable = false )
    private String body;

    @Column( nullable = false, name = "correct_answers" )
    private String correctAnswers; // separated by the DELIM


    @Column( nullable = false, name = "wrong_answers" )
    private String wrongAnswers; // split by the DELIM;

    @Column( name = "user_answers" )
    private String userAnswers; // split by the DELIM;

    @Enumerated( EnumType.STRING )
    @Column( name = "quiz_type" )
    private QuizType quizType;

    @ManyToOne
    private Card card;

    @ManyToOne
    @Column( name = "quiz_history", nullable = false )
    private QuizHistory quizHistory;


//    @PrimaryKeyJoinColumn( name = "created_by" )
//    @OneToOne
//    private User createdBy;


//    @PrimaryKeyJoinColumn( name = "updated_by" )
//    @OneToOne
//    private User updatedBy;

//    @Column( name = "correct_answer_count", columnDefinition = "smallint default 0" )
//    private int correctAnswerCount;

}
