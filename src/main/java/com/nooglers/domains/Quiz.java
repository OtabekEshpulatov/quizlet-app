package com.nooglers.domains;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Quiz {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private short deleted;
    private Integer createdBy;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
}
