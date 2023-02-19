package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "class")
@Table
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class extends BaseDomain {

    @CreationTimestamp
    @Column(columnDefinition = "timestamp default current_timestamp", name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(columnDefinition = "smallint default 0")
    private short deleted;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private User createdBy;

    @Column( nullable = false )
    private String name;

    @Column
    private String description;

    @Column( name = "permission_to_invite", nullable = false, columnDefinition = "boolean default true" )
    private boolean permissionToInvite;

    @Column( name = "permission_to_update_sets", nullable = false, columnDefinition = "boolean default true" )
    private boolean permissionToUpdateSets;

    @Column( name = "school_name", nullable = false )
    private String schoolName;

    @Column( nullable = false, name = "invitation_link" )
    private String invitationLink;


}
