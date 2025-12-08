# Android Image Upload & Profile Management

Ứng dụng Android quản lý thông tin người dùng và ảnh đại diện, sử dụng cơ sở dữ liệu nội bộ SQLite.

## 🌟 Chức năng chính

1.  **Đăng ký & Đăng nhập (Register & Login)**
    *   Cho phép người dùng tạo tài khoản mới với các thông tin: Tên đăng nhập, Mật khẩu, Họ tên, Email, Giới tính.
    *   Xác thực đăng nhập từ cơ sở dữ liệu SQLite.

2.  **Hồ sơ cá nhân (Profile)**
    *   Hiển thị thông tin chi tiết của người dùng sau khi đăng nhập thành công.
    *   Hiển thị ảnh đại diện (Avatar).
    *   Chức năng Đăng xuất.

3.  **Quản lý Ảnh đại diện**
    *   Từ trang Hồ sơ, nhấn vào ảnh đại diện để chuyển sang màn hình cập nhật ảnh.
    *   **Chọn File**: Mở thư viện ảnh của điện thoại để chọn ảnh mới.
    *   **Upload/Lưu**: Lưu đường dẫn ảnh vào cơ sở dữ liệu SQLite (thay thế ảnh cũ).
    *   Nút **Back**: Quay lại trang hồ sơ để xem kết quả.

## 🛠 Công nghệ sử dụng

*   **Ngôn ngữ**: Java
*   **Database**: SQLite (Lưu trữ user và đường dẫn ảnh)
*   **Thư viện ảnh**: Glide (Hiển thị ảnh từ đường dẫn file)
*   **Networking**: Retrofit (Được tích hợp sẵn cho các tính năng mở rộng upload lên server)
*   **Giao diện**: XML Layouts, Material Design Components

## 📂 Cấu trúc dự án

*   `LoginActivity`: Màn hình đăng nhập.
*   `RegisterActivity`: Màn hình đăng ký.
*   `ProfileActivity`: Màn hình hiển thị thông tin user.
*   `MainActivity`: Màn hình chọn và lưu ảnh đại diện.
*   `DatabaseHelper`: Lớp quản lý SQLite (tạo bảng, thêm, sửa, xóa, lấy dữ liệu user).
*   `utils/RealPathUtil`: Tiện ích giúp lấy đường dẫn thực (absolute path) của file ảnh từ URI.

## 🚀 Cách chạy ứng dụng

1.  Mở project bằng **Android Studio**.
2.  Đợi Gradle sync hoàn tất.
3.  Kết nối thiết bị thật hoặc máy ảo (Emulator).
4.  Nhấn **Run** (Shift + F10).
5.  **Quy trình test**:
    *   Vào màn hình Đăng ký -> Tạo tài khoản -> Chuyển về Đăng nhập.
    *   Đăng nhập tài khoản vừa tạo.
    *   Xem thông tin tại trang Hồ sơ.
    *   Nhấn vào hình tròn Avatar -> Chọn ảnh -> Nhấn Upload.
    *   Nhấn Back để thấy ảnh đại diện mới được cập nhật.

## 📝 Ghi chú

*   Ứng dụng yêu cầu quyền truy cập bộ nhớ (`READ_EXTERNAL_STORAGE` / `READ_MEDIA_IMAGES`) để lấy ảnh.
*   Trên Android 13+ (API 33+), quyền truy cập media được xử lý riêng biệt.

<img width="811" height="614" alt="image" src="https://github.com/user-attachments/assets/cf3f70dd-edd4-4c43-9487-1d1fc9d385a1" />


<img width="773" height="623" alt="image" src="https://github.com/user-attachments/assets/2e8c9155-c825-46aa-ad4c-e716faf89d6f" />
