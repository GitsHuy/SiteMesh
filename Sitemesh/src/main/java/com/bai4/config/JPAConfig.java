package com.bai4.config; // Quan trọng: Tên package phải khớp

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {

    private static EntityManagerFactory factory;

    /**
     * Lấy một EntityManager để thao tác với cơ sở dữ liệu.
     * @return một đối tượng EntityManager.
     */
    public static EntityManager getEntityManager() {
        // Kiểm tra xem factory đã được khởi tạo hoặc còn mở không
        if (factory == null || !factory.isOpen()) {
            // Tên "my-persistence-unit" phải khớp 100% với tên trong file persistence.xml
            factory = Persistence.createEntityManagerFactory("my-persistence-unit");
        }
        return factory.createEntityManager();
    }

    /**
     * Đóng EntityManagerFactory khi ứng dụng kết thúc.
     */
    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}