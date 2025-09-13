package com.bai4.dao;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CheckConnection {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        try {
            // Dòng này sẽ đọc file persistence.xml và cố gắng tạo kết nối
            // Tên "my-persistence-unit" phải khớp với trong file XML
            emf = Persistence.createEntityManagerFactory("my-persistence-unit");
            
            // Nếu dòng trên chạy qua mà không có lỗi, tức là kết nối THÀNH CÔNG!
            System.out.println("✅✅✅ KẾT NỐI DATABASE THÀNH CÔNG! ✅✅✅");

        } catch (Exception e) {
            // Nếu có lỗi, nó sẽ được bắt ở đây
            System.err.println("❌❌❌ KẾT NỐI DATABASE THẤT BẠI! ❌❌❌");
            e.printStackTrace(); // In chi tiết lỗi ra console
        } finally {
            if (emf != null) {
                emf.close(); // Luôn đóng kết nối sau khi dùng xong
            }
        }
    }
}