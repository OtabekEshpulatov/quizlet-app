package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity(name = "document")
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document extends BaseDomain{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column( nullable = false, name = "generated_file_name" )
    private String generatedFileName;
    @Column( nullable = false, name = "original_file_name" )
    private String originalFileName;
    @Column( nullable = false, name = "mime_type" )
    private String mimeType;
    @Column( nullable = false, name = "file_path" )
    private String filePath;
    @Column( nullable = false, name = "file_size" )
    private long fileSize;
    @Column( nullable = false )
    private String extension;
    @OneToOne(mappedBy = "document",cascade = CascadeType.ALL)
    private Card card;
}

