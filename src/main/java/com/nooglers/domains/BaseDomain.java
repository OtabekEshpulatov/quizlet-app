package com.nooglers.domains;

import jakarta.persistence.Id;

import java.time.LocalDateTime;


public abstract class BaseDomain {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private short deleted;
    @Id
    private Integer id;

}
