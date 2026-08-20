package com.example.interview.web;

import java.util.List;

import com.example.interview.account.AppUser;
import com.example.interview.account.AppUserRepository;
import com.example.interview.account.UserService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiAccountController {

    private final UserService userService;
    private final AppUserRepository users;

    public ApiAccountController(UserService userService, AppUserRepository users) {
        this.userService = userService;
        this.users = users;
    }

    @GetMapping("/account")
    public AccountResponse account(@AuthenticationPrincipal Jwt jwt) {
        AppUser user = userService.findByUsername(jwt.getSubject());
        return AccountResponse.from(user);
    }

    @GetMapping("/admin/users")
    public List<AccountResponse> users() {
        return users.findAll().stream()
                .map(AccountResponse::from)
                .toList();
    }

    public record AccountResponse(Long id, String username, String fullName, String role) {

        static AccountResponse from(AppUser user) {
            return new AccountResponse(user.getId(), user.getUsername(), user.getFullName(), user.getRole().name());
        }
    }
}
