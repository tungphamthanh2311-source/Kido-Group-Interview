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

    // Tao du lieu mau khi ung dung khoi dong lan dau.
    // Neu DB da co user thi khong seed lai de tranh ghi de password nguoi dung da doi.
    @Bean
    CommandLineRunner seedUsers(AppUserRepository users, PasswordEncoder passwordEncoder) {
        return args -> {
            if (users.count() > 0) {
                return;
            }

            // Hai tai khoan demo de test phan quyen: admin co ADMIN, user co USER.
            users.save(new AppUser("admin", passwordEncoder.encode("admin123"), "Admin User", Role.ADMIN));
            users.save(new AppUser("user", passwordEncoder.encode("user123"), "Regular User", Role.USER));
        };
    }
}
