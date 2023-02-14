package com.nooglers.domains.test;

import com.nooglers.domains.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column( nullable = false )
    private String body;
    @Column( columnDefinition = "timestamp with timezone default current_timestamp", name = "created_at" )
    private LocalDateTime createdAt;

    @Column( columnDefinition = "timestamp with time zone", name = "updated_at" )
    private LocalDateTime updatedAt;
    @Column( columnDefinition = "smallint default 0" )
    private short deleted;


    //    @Column( name = "created_by" )
    @OneToOne
    private User createdBy;


    @PrimaryKeyJoinColumn( name = "updated_by" )
    @OneToOne
    private User updatedBy;

    @Column( name = "correct_answer_count", columnDefinition = "smallint default 0" )
    private int correctAnswerCount;

}
