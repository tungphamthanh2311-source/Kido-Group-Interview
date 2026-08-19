package com.example.interview.web;

import com.example.interview.account.AppUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    // Trang admin doc danh sach user de minh hoa phan quyen ADMIN/USER.
    private final AppUserRepository users;

    public AdminController(AppUserRepository users) {
        this.users = users;
    }

    @GetMapping("/admin/users")
    public String users(Model model) {
        // URL nay da duoc chan trong SecurityConfig: chi ADMIN moi vao duoc.
        model.addAttribute("users", users.findAll());
        return "admin-users";
    }
}
