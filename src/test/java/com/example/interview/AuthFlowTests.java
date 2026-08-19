package com.example.interview;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.interview.account.AppUser;
import com.example.interview.account.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AuthFlowTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppUserRepository users;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void resetSeedPasswords() {
        AppUser admin = users.findByUsername("admin").orElseThrow();
        admin.setPassword(passwordEncoder.encode("admin123"));
        users.save(admin);

        AppUser user = users.findByUsername("user").orElseThrow();
        user.setPassword(passwordEncoder.encode("user123"));
        users.save(user);
    }

    @Test
    void anonymousUserIsRedirectedToLogin() throws Exception {
        mockMvc.perform(get("/account"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"))
                .andExpect(unauthenticated());
    }

    @Test
    void userCanLoginWithSeededCredentials() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "user")
                        .param("password", "user123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/account"))
                .andExpect(authenticated().withUsername("user"));
    }

    @Test
    void regularUserCannotOpenAdminPage() throws Exception {
        mockMvc.perform(get("/admin/users")
                        .with(user("user").roles("USER")))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminCanOpenUserManagementPage() throws Exception {
        mockMvc.perform(get("/admin/users")
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    void userCanChangePassword() throws Exception {
        mockMvc.perform(post("/password")
                        .with(user("user").roles("USER"))
                        .with(csrf())
                        .param("currentPassword", "user123")
                        .param("newPassword", "newUser123")
                        .param("confirmPassword", "newUser123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/account"));

        AppUser user = users.findByUsername("user").orElseThrow();
        assertThat(passwordEncoder.matches("newUser123", user.getPassword())).isTrue();
    }

    @Test
    void changePasswordRejectsWrongCurrentPassword() throws Exception {
        mockMvc.perform(post("/password")
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf())
                        .param("currentPassword", "wrong")
                        .param("newPassword", "newAdmin123")
                        .param("confirmPassword", "newAdmin123"))
                .andExpect(status().isOk());

        AppUser admin = users.findByUsername("admin").orElseThrow();
        assertThat(passwordEncoder.matches("admin123", admin.getPassword())).isTrue();
    }
}
