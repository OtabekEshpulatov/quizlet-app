package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.IdGeneratorType;

import java.time.LocalDateTime;
import java.util.List;

@Entity()
@Table( name = "card" )
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card extends BaseDomain {

    @CreationTimestamp
    @Column( columnDefinition = "timestamp default current_timestamp" )
    private LocalDateTime createdAt;


    @Column( columnDefinition = "timestamp with time zone", name = "updated_at" )
    private LocalDateTime updatedAt;
    @Column( columnDefinition = "smallint default 0" )
    private short deleted;
    @Id

    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @OneToOne
    private User createdBy;
    @ManyToOne
    private Module module;

    @Column( nullable = false )
    private String title;
    @Column( nullable = false )
    private String description;

}
