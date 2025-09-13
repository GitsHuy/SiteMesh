package com.bai4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User_profile") // Tên của bảng trong cơ sở dữ liệu
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Cấu hình ID tự tăng
    private Long id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "email", unique = true, nullable = false) // Email là duy nhất và không được null
    private String email;
    
    // --- Các trường bạn vừa thêm ---
    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "image_path")
    private String imagePath; // Sẽ lưu tên file ảnh (ví dụ: "avatar.png")

    
    // --- Constructors (Hàm khởi tạo) ---
    public User() {
        // Constructor rỗng là bắt buộc đối với JPA
    }

    public User(String fullName, String email, String phone, String imagePath) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.imagePath = imagePath;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}