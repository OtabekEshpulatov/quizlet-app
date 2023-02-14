package com.nooglers.domains.test;

import com.nooglers.domains.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Variant {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @ManyToOne
    private Question question;

    @Column( nullable = false )
    private String body;

    @Column( name = "is_correct", nullable = false, columnDefinition = "bool default false" )
    private boolean isCorrect;

    //    @Column( name = "created_by" )
    @OneToOne
    private User createdBy;
    @Column( columnDefinition = "timestamp with time zone default current_timestamp", name = "created_at" )
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column( columnDefinition = "timestamp with time zone", name = "updated_at" )
    @CreationTimestamp
    private LocalDateTime updatedAt;

    //    @Column( name = "updated_by" )
    @OneToOne
    private User updatedBy;


    @Column( columnDefinition = "smallint default 0" )
    private short deleted;
}
