package com.huntech.ms.auth_service.configuration.data;

import com.huntech.ms.auth_service.model.Role;
import com.huntech.ms.auth_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        createDefaultRoles();
    }

    private void createDefaultRoles() {
        // Lista de roles predeterminados
        List<Role> defaultRoles = Arrays.asList(
                new Role(1L, "ADMIN", true),
                new Role(2L, "SUPPORT_AGENT", true),
                new Role(3L, "CLIENT", true)
        );

        // Contador para ver cuántos roles se crearon
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
}
