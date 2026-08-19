package com.example.interview.web;

import com.example.interview.account.AppUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final AppUserRepository users;

    public AdminController(AppUserRepository users) {
        this.users = users;
    }

    @GetMapping("/admin/users")
    public String users(Model model) {
        model.addAttribute("users", users.findAll());
        return "admin-users";
    }
}
