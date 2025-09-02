package com.huntech.ms.auth_service.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
@Table(name = "tb_user")
@Entity
public class User {

        @Id
        @EqualsAndHashCode.Include
        private Integer idUser;

        @Column(nullable = false, length = 25, unique = true)
        private String username;

        @Column(nullable = false, length = 60)
        private String password;

        @Column(nullable = false)
        private boolean enabled;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "id_role", nullable = false, foreignKey = @ForeignKey(name = "fk_user_role"))
        private Role role;
}
