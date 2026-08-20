---
name: auth-interview-practice
description: Implement, review, or explain this project's Spring Boot Authentication and User Management interview-practice app, including session login, JWT-protected APIs, account details, password changes, role-based Admin/User authorization, seeded demo users, and focused tests.
---

# Auth Interview Practice

Use this skill when working on the interview-practice authentication app in this repository.

## Project Rules

- Keep the app intentionally small and explainable for interviews.
- Prefer Spring Boot, Spring Security, Thymeleaf, Spring Data JPA, and H2 unless the user asks for another stack.
- Keep credentials and behavior easy to demonstrate locally: `admin/admin123` has `ADMIN`, `user/user123` has `USER`.
- Use form login and HTTP session for Thymeleaf pages; use stateless Bearer JWT for `/api/**`.
- Keep `/login`, `/api/auth/token`, and static assets public. Protect all other routes.
- Restrict `/admin/**` to `ADMIN`.
- Restrict `/api/admin/**` to JWTs containing the `ADMIN` role.
- Keep demo JWT access tokens short-lived and never put passwords or sensitive data in claims.
- Require the current password before changing a password.
- Store passwords with `PasswordEncoder`, never plaintext.
- Add or update focused tests for login, role authorization, and password-change behavior.

## Explanation Checklist

When explaining the code, cover:

- Authentication: how Spring Security loads a user and validates the password.
- Authorization: how URL rules map `ADMIN` and `USER` roles to protected pages.
- JWT: how the API filter chain verifies the RSA signature, expiry, subject, and `roles` claim without an HTTP session.
- Persistence: how H2 and JPA store demo users.
- Password change: why the old password is checked before saving the encoded new password.
- Limits: this is interview-practice code, not production-ready account recovery, audit logging, lockout, or MFA.
