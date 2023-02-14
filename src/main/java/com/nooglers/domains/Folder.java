package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "Folder")
@Table(name = "folder")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Folder extends BaseDomain {
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp with  time zone default current_timestamp", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(columnDefinition = "smallint default 0")
    private short deleted;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;


    @Column(nullable = false)
    private String description;


    @OneToOne
    private User createdBy;

}
