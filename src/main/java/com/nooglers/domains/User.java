package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity( name = "Users" )
@Table( name = "users" )
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseDomain {

    @CreationTimestamp
    @Column( columnDefinition = "timestamp with time zone default current_timestamp", name = "created_at" )
    private LocalDateTime createdAt;

    @Column( columnDefinition = "timestamp with time zone", name = "updated_at" )
    private LocalDateTime updatedAt;

    @Column( columnDefinition = "smallint default 0", nullable = false )
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
