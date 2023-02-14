package com.nooglers.domains.test;

import com.nooglers.domains.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name="quiz_history")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizHistory {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( name = "created_at", columnDefinition = "timestamp with time zone default current_timestamp" )
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column( name = "updated_at", columnDefinition = "timestamp with time zone" )
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @Column( name = "started_at", columnDefinition = "timestamp with time zone" )
    private LocalDateTime startedAt;

    @Column( name = "finished_at", columnDefinition = "timestamp with time zone" )
    private LocalDateTime finishedAt;

    @Column( columnDefinition = "smallint default 0", nullable = false )
    private short deleted;

 
    @OneToOne
    private User createdBy;

    @Column( columnDefinition = "smallint default 0", nullable = false, name = "total_question_count" )
    private int totalQuestionCount;

    @Column( columnDefinition = "smallint default 0", nullable = false,name = "total_answer_count")
    private int correctAnswerCount;


}
