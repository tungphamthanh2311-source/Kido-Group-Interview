package com.example.interview.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Spring Security xu ly POST /login; controller nay chi tra ve giao dien form dang nhap.
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
