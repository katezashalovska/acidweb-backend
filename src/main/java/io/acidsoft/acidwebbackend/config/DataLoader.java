package io.acidsoft.acidwebbackend.config;

import io.acidsoft.acidwebbackend.entity.User;
import io.acidsoft.acidwebbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${acidsoft.admin.username:}")
    private String adminUsername;

    @Value("${acidsoft.admin.password:}")
    private String adminPassword;

    @Value("${acidsoft.admin.email:}")
    private String adminEmail;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            log.info("Database already contains users. Skipping automatic admin initialization.");
            return;
        }

        if (!StringUtils.hasText(adminUsername) || !StringUtils.hasText(adminPassword)) {
            log.warn("No admin user exists in DB and ACIDSOFT_ADMIN_USERNAME / ACIDSOFT_ADMIN_PASSWORD environment variables are not set. Admin user creation skipped.");
            return;
        }

        String email = StringUtils.hasText(adminEmail) ? adminEmail : adminUsername + "@acidsoft.io";

        User admin = User.builder()
                .username(adminUsername)
                .email(email)
                .passwordHash(passwordEncoder.encode(adminPassword))
                .role("ROLE_ADMIN")
                .build();

        userRepository.save(admin);
        log.info("Initial admin user created successfully for username: {}", adminUsername);
    }
}

