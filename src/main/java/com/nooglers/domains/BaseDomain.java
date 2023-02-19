package com.nooglers.domains;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
public abstract class BaseDomain {

//    @Column( name = "created_at", nullable = false, columnDefinition = "timestamp with time zone default current_timestamp  " )
//    private LocalDateTime createdAt;
//
//
//    @Column( name = "updated_at" )
//    private LocalDateTime updatedAt;
//
//
//    @Column( columnDefinition = "smallint default 0", nullable = false )
//
//    private short deleted;
//    @Id
//    @GeneratedValue( strategy = GenerationType.IDENTITY )
//    private Integer id;
//
//
//    @Column( name = "updated_by" )
//    @OneToOne
//    private User updatedBy;
}

