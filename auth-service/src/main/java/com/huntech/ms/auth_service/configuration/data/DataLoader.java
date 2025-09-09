package com.huntech.ms.auth_service.configuration.data;

import com.huntech.ms.auth_service.model.Role;
import com.huntech.ms.auth_service.model.User;
import com.huntech.ms.auth_service.repository.RoleRepository;
import com.huntech.ms.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createDefaultRoles();
        createDefaultUsers();
    }

    private void createDefaultRoles() {
        // Lista de roles predeterminados
        List<Role> defaultRoles = Arrays.asList(
                new Role(1L, "ADMIN", true),
                new Role(2L, "SUPPORT_AGENT", true),
                new Role(3L, "CLIENT", true)
        );

        int createdCount = 0;
        for (Role role : defaultRoles) {
            if (!roleRepository.existsByName(role.getName())) {
                roleRepository.save(role);
                createdCount++;
                System.out.println("✅ Created role: " + role.getName());
            }
        }

        if (createdCount > 0) {
            System.out.println("🎉 Total roles created: " + createdCount);
        } else {
            System.out.println("📊 All roles already exist in database");
        }
    }

    private void createDefaultUsers() {
        // Obtener roles de la base de datos
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("ADMIN role not found"));
        Role supportRole = roleRepository.findByName("SUPPORT_AGENT")
                .orElseThrow(() -> new RuntimeException("SUPPORT_AGENT role not found"));
        Role clientRole = roleRepository.findByName("CLIENT")
                .orElseThrow(() -> new RuntimeException("CLIENT role not found"));

        // Lista de usuarios predeterminados
        List<User> defaultUsers = Arrays.asList(
                createUser(1L, "admin", "admin123", true, adminRole),
                createUser(2L, "support1", "support123", true, supportRole),
                createUser(3L, "support2", "support456", true, supportRole),
                createUser(4L, "client1", "client123", true, clientRole),
                createUser(5L, "client2", "client456", true, clientRole)
        );

        int createdCount = 0;
        for (User user : defaultUsers) {
            if (!userRepository.existsByUsername(user.getUsername())) {
                userRepository.save(user);
                createdCount++;
                System.out.println("✅ Created user: " + user.getUsername());
            }
        }

        if (createdCount > 0) {
            System.out.println("🎉 Total users created: " + createdCount);
        } else {
            System.out.println("📊 All users already exist in database");
        }

        // Mostrar credenciales de prueba
        System.out.println("\n🔐 Test Credentials:");
        System.out.println("👤 ADMIN: admin / admin123");
        System.out.println("👤 SUPPORT: support1 / support123");
        System.out.println("👤 CLIENT: client1 / client123");
    }

    private User createUser(Long id, String username, String password, boolean enabled, Role role) {
        User user = new User();
        user.setIdUser(id.intValue());
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(enabled);
        user.setRole(role);
        return user;
    }
}
