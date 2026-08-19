package com.example.interview.web;

import java.security.Principal;

import com.example.interview.account.AppUser;
import com.example.interview.account.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/account";
    }

    @GetMapping("/account")
    public String account(Model model, Principal principal) {
        AppUser user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "account";
    }

    @GetMapping("/password")
    public String changePasswordForm(Model model) {
        model.addAttribute("passwordForm", new PasswordChangeForm());
        return "password";
    }

    @PostMapping("/password")
    public String changePassword(
            @Valid @ModelAttribute("passwordForm") PasswordChangeForm form,
            BindingResult bindingResult,
            Principal principal,
            RedirectAttributes redirectAttributes) {
        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match");
        }

        if (bindingResult.hasErrors()) {
            return "password";
        }

        boolean changed = userService.changePassword(
                principal.getName(),
                form.getCurrentPassword(),
                form.getNewPassword());

        if (!changed) {
            bindingResult.rejectValue("currentPassword", "password.current.invalid", "Current password is incorrect");
            return "password";
        }

        redirectAttributes.addFlashAttribute("success", "Password changed successfully");
        return "redirect:/account";
    }

    public static class PasswordChangeForm {

        @NotBlank
        private String currentPassword = "";

        @NotBlank
        @Size(min = 6)
        private String newPassword = "";

        @NotBlank
        private String confirmPassword = "";

        public String getCurrentPassword() {
            return currentPassword;
        }

        public void setCurrentPassword(String currentPassword) {
            this.currentPassword = currentPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }
    }
}
