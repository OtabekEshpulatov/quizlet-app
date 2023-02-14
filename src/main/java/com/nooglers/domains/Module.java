package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity( name = "Module" )
@Table( name = "module" )
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module extends BaseDomain {

    @Column( name = "created_at", columnDefinition = "timestamp with time zone default current_timestamp", nullable = false )
    private LocalDateTime createdAt;

    @Column( name = "updated_at", columnDefinition = "timestamp with time zone" )
    private LocalDateTime updatedAt;
    @Column( columnDefinition = "smallint default 0" )
    private short deleted;
    @OneToOne

    private User createdBy;
    @ManyToMany
    private List<Folder> folder;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column( nullable = false )
    private String name;

    @Column( name = "is_public", columnDefinition = "bool default true", nullable = false )
    private boolean isPublic;

}
