package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity( name = "Users" )
@Table( name = "users" )
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseDomain {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private short deleted;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column( unique = true, nullable = false, updatable = false )
    private String email;
    @Column( unique = true, nullable = false, updatable = false )
    private String username;
    @Column( nullable = false )
    private String password;


}
