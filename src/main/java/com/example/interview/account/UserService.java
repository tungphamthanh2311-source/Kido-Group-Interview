package com.example.interview.account;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    // Repository phu trach doc/ghi user trong database.
    private final AppUserRepository users;

    // PasswordEncoder dung BCrypt, khong luu mat khau dang plain text.
    private final PasswordEncoder passwordEncoder;

    public UserService(AppUserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser findByUsername(String username) {
        return users.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    // Transaction dam bao viec doi mat khau la mot thao tac ghi DB hoan chinh.
    // Tra ve false neu mat khau hien tai sai, controller se hien thi loi cho nguoi dung.
    @Transactional
    public boolean changePassword(String username, String currentPassword, String newPassword) {
        AppUser user = findByUsername(username);

        // So sanh raw password nguoi dung nhap voi hash dang luu trong DB.
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return false;
        }

        // Chi luu password moi sau khi da ma hoa.
        user.setPassword(passwordEncoder.encode(newPassword));
        return true;
    }
}
