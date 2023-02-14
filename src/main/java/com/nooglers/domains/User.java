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
public class User extends BaseDomain {

    @Column(columnDefinition = "timestamp with time zone default current_timestamp", nullable = true)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "smallint default 0")
    private short deleted;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column( unique = true, nullable = false )
    private String email;
    @Column( unique = true, nullable = false )
    private String username;
    @Column( nullable = false )
    private String password;


}
