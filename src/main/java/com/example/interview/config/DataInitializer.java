package com.example.interview.config;

import com.example.interview.account.AppUser;
import com.example.interview.account.AppUserRepository;
import com.example.interview.account.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedUsers(AppUserRepository users, PasswordEncoder passwordEncoder) {
        return args -> {
            if (users.count() > 0) {
                return;
            }
            users.save(new AppUser("admin", passwordEncoder.encode("admin123"), "Admin User", Role.ADMIN));
            users.save(new AppUser("user", passwordEncoder.encode("user123"), "Regular User", Role.USER));
        };
    }
}
