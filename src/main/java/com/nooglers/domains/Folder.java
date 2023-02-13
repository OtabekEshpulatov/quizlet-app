package com.nooglers.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity( name = "Folder" )
@Table( name = "folder" )
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Folder extends BaseDomain {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private short deleted;
    @Id
    private Integer id;
    private String name;

    private Integer createdBy;

}
