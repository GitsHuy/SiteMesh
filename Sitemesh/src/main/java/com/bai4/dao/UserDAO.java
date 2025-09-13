package com.bai4.dao;

import com.bai4.config.JPAConfig;
import com.bai4.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class UserDAO {

    /**
     * Thêm mới một đối tượng User vào cơ sở dữ liệu.
     * @param user Đối tượng User cần thêm.
     */
    public void create(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(user); // Dùng persist để thêm mới
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Cập nhật thông tin một User đã tồn tại.
     * @param user Đối tượng User với thông tin đã được cập nhật.
     */
    public void update(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(user); // Dùng merge để cập nhật đối tượng đã có trong CSDL
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Xóa một User khỏi cơ sở dữ liệu dựa trên id.
     * @param id ID của User cần xóa.
     */
    public void delete(long id) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user); // Dùng remove để xóa
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Tìm một User theo ID.
     * @param id ID của User cần tìm.
     * @return Đối tượng User tìm thấy hoặc null nếu không tồn tại.
     */
    public User findById(long id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Lấy danh sách tất cả các User trong cơ sở dữ liệu.
     * @return Một List chứa tất cả các đối tượng User.
     */
    public List<User> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            // JPQL (Java Persistence Query Language) để truy vấn đối tượng
            String jpql = "SELECT u FROM User u";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            return query.getResultList();
        } finally {
            em.close();
        }  
    }
    /**
     * Tìm kiếm danh sách User theo tên (fullName).
     * @param fullName Tên cần tìm kiếm (có thể chứa ký tự phần nào của tên).
     * @return Danh sách User khớp với tên, hoặc danh sách rỗng nếu không tìm thấy.
     */
    public List<User> findByFullName(String fullName) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.fullName LIKE :fullName";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("fullName", "%" + fullName + "%"); // Tìm kiếm không phân biệt vị trí
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Tìm kiếm danh sách User theo email.
     * @param email Email cần tìm kiếm (có thể chứa ký tự phần nào của email).
     * @return Danh sách User khớp với email, hoặc danh sách rỗng nếu không tìm thấy.
     */
    public List<User> findByEmail(String email) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email LIKE :email";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("email", "%" + email + "%"); // Tìm kiếm không phân biệt vị trí
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Tìm kiếm danh sách User theo số điện thoại (phone).
     * @param phone Số điện thoại cần tìm kiếm (có thể chứa ký tự phần nào của số).
     * @return Danh sách User khớp với số điện thoại, hoặc danh sách rỗng nếu không tìm thấy.
     */
    public List<User> findByPhone(String phone) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.phone LIKE :phone";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("phone", "%" + phone + "%"); // Tìm kiếm không phân biệt vị trí
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}