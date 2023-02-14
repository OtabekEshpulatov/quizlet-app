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

    private LocalDateTime createdAt;
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
    private boolean isPublic;

}
