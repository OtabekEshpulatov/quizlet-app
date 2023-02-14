package com.nooglers.domains.test;

import com.nooglers.domains.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table( name = "quiz_history" )
public class QuizHistory {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( name = "created_at", columnDefinition = "timestamp with time zone default current_timestamp" )
    private LocalDateTime createdAt;
    @Column( name = "updated_at", columnDefinition = "timestamp with time zone" )
    private LocalDateTime updatedAt;

    @Column( name = "started_at", columnDefinition = "timestamp with time zone" )
    private LocalDateTime startedAt;

    @Column( name = "finished_at", columnDefinition = "timestamp with time zone" )
    private LocalDateTime finishedAt;

    @Column( columnDefinition = "smallint default 0", nullable = false )
    private short deleted;

//    @Column( columnDefinition = "created_by", nullable = false )
    @OneToOne
    private User createdBy;

    @Column( columnDefinition = "smallint default 0", nullable = false )
    private int totalQuestionCount;

    @Column( columnDefinition = "smallint default 0", nullable = false )
    private int correctAnswerCount;


}
