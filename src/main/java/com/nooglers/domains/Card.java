package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity( name = "User" )
@Table( name = "card" )
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card extends BaseDomain {

    @Column( columnDefinition = "timestamp default current_timestamp" )
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Column( columnDefinition = "short default 0" )
    private short deleted;
    @Id
    private Integer id;


    //    @Column( columnDefinition = "references users(id)" )
    private Integer createdBy;

    @Column( nullable = false )
    private String title;
    private String description;

}
