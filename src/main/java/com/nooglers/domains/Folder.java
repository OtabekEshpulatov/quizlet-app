package com.nooglers.domains;

import com.nooglers.enums.FolderStatus;
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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp with  time zone default current_timestamp", nullable = true, insertable = true, updatable = false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    //    @Column(columnDefinition = "smallint default 0")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar default 'CREATED'")
    private FolderStatus status;

    @Column(nullable = false)
    private String title;


    @Column(nullable = true)
    private String description;


    @OneToOne
    private User createdBy;

}
