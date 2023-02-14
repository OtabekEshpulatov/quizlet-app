package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity()
@Table( name = "class" )
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class extends BaseDomain {


    @Column( columnDefinition = "timestamp default current_timestamp" )
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column( columnDefinition = "smallint default 0" )
    private short deleted;
    @Id
    private Integer id;
    @OneToOne
    private User createdBy;

}
