package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "module")
@Table
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Module extends BaseDomain {

    @Column(name = "created_at", columnDefinition = "timestamp with time zone default current_timestamp", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp with time zone")

    private LocalDateTime updatedAt;
    @Column(columnDefinition = "smallint default 0")
    private short deleted;
    @OneToOne
    private User createdBy;
    @ManyToMany
    private List<Folder> folder;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    @Column(name = "is_public", columnDefinition = "bool default true", nullable = false)
    private boolean isPublic;

    @ManyToMany
    private List<Class> moduleClass;


    private String description;

}
