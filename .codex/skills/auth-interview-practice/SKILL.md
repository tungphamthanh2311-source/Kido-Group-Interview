---
name: auth-interview-practice
description: Triển khai, rà soát hoặc giải thích ứng dụng luyện phỏng vấn Spring Boot về xác thực và quản lý người dùng của dự án này, bao gồm đăng nhập bằng session, API bảo vệ bằng JWT, thông tin tài khoản, đổi mật khẩu, phân quyền Admin/User, dữ liệu người dùng mẫu và các test trọng tâm.
---

# Luyện phỏng vấn về xác thực

Sử dụng skill này khi làm việc với ứng dụng luyện phỏng vấn về xác thực trong repository này.

## Quy tắc dự án

- Giữ ứng dụng nhỏ gọn, có chủ đích và dễ giải thích khi phỏng vấn.
- Ưu tiên Spring Boot, Spring Security, Thymeleaf, Spring Data JPA và H2, trừ khi người dùng yêu cầu công nghệ khác.
- Giữ thông tin đăng nhập và hành vi dễ trình diễn ở môi trường local: `admin/admin123` có quyền `ADMIN`, `user/user123` có quyền `USER`.
- Dùng form login và HTTP session cho các trang Thymeleaf; dùng Bearer JWT stateless cho `/api/**`.
- Cho phép truy cập công khai `/login`, `/api/auth/token` và tài nguyên tĩnh. Bảo vệ tất cả route còn lại.
- Chỉ cho phép `ADMIN` truy cập `/admin/**`.
- Chỉ cho phép JWT có role `ADMIN` truy cập `/api/admin/**`.
- Giữ thời gian sống của JWT demo ngắn và không bao giờ đưa mật khẩu hoặc dữ liệu nhạy cảm vào claims.
- Yêu cầu mật khẩu hiện tại trước khi đổi mật khẩu.
- Lưu mật khẩu bằng `PasswordEncoder`, không bao giờ lưu plaintext.
- Thêm hoặc cập nhật các test trọng tâm cho đăng nhập, phân quyền theo role và đổi mật khẩu.

## Nội dung cần giải thích

Khi giải thích code, cần trình bày:

- Xác thực: cách Spring Security tải người dùng và kiểm tra mật khẩu.
- Phân quyền: cách các rule URL ánh xạ role `ADMIN` và `USER` vào những trang được bảo vệ.
- JWT: cách API filter chain xác minh chữ ký RSA, thời gian hết hạn, subject và claim `roles` mà không dùng HTTP session.
- Lưu trữ: cách H2 và JPA lưu các tài khoản demo.
- Đổi mật khẩu: lý do phải kiểm tra mật khẩu cũ trước khi lưu mật khẩu mới đã được encode.
- Giới hạn: đây là code luyện phỏng vấn, chưa phải hệ thống production có khôi phục tài khoản, audit log, khóa tài khoản hoặc MFA.
