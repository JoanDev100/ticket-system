package com.huntech.ms.auth_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Builder(toBuilder = true)
public class Role {
    @Id
    @EqualsAndHashCode.Include
    private Long idRole;
    @Column(nullable = false, length = 25)
    private String name;
    @Column(nullable = false)
    private boolean enabled;
}
