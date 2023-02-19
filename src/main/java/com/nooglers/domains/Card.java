package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "card")
@Table
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Card extends BaseDomain {
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default current_timestamp")
    private transient LocalDateTime createdAt;
    @CreationTimestamp
    @Column( columnDefinition = "timestamp with time zone", name = "updated_at" )
    private transient LocalDateTime updatedAt;
    @Column(columnDefinition = "boolean default 'false'")
    private boolean deleted;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @ManyToOne
    private Module module;
    @Column(nullable = false)
    private String term;
    @Column(nullable = false)
    private String description;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "document_id")
    private Document document;
}
