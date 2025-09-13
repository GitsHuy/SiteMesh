package com.bai4.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.bai4.dao.UserDAO;
import com.bai4.entity.User;

@WebServlet("/profile")
@MultipartConfig // Annotation để servlet có thể xử lý file upload
public class ProfileServlet extends HttpServlet {
    private UserDAO userDAO;
    private static final String UPLOAD_DIRECTORY = "uploads"; // Thư mục lưu ảnh

    @Override
    public void init() {
        this.userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Giả sử bạn đã có user đăng nhập và lưu trong session
        // Tạm thời, ta lấy user có id=1 để làm ví dụ
    	/*
        User user = userDAO.findById(1L); // Thay bằng logic lấy user từ session

        request.setAttribute("user", user);*/
        request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	String action = request.getParameter("action");
        String idParam = request.getParameter("id");
        String fullName = request.getParameter("fullName") != null ? request.getParameter("fullName").trim() : "";
        String phone = request.getParameter("phone") != null ? request.getParameter("phone").trim() : "";
        String email = request.getParameter("email") != null ? request.getParameter("email").trim() : "";

        User user = null;
        if (idParam != null && !idParam.isEmpty()) {
            try {
                user = userDAO.findById(Long.parseLong(idParam));
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid ID format!");
                doGet(request, response);
                return;
            }
        }

        if (user == null && !"create".equals(action)) {
            request.setAttribute("error", "User not found!");
            doGet(request, response);
            return;
        }
        try {
            if (user != null) {
                user.setFullName(fullName);
                user.setPhone(phone);
                user.setEmail(email); // Cập nhật email
            }

            // Xử lý upload file ảnh
            Part filePart = request.getPart("image");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            if (fileName != null && !fileName.isEmpty()) {
                String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
                Path uploadDirPath = Paths.get(uploadPath);

                if (!Files.exists(uploadDirPath)) {
                    Files.createDirectories(uploadDirPath);
                }

                filePart.write(uploadPath + "/" + fileName);
                if (user != null) {
                    user.setImagePath(fileName);
                }
            }
            
         // Xử lý logic dựa trên action
            if ("update".equals(action)) {
                if (user != null) {
                    userDAO.update(user);
                    request.setAttribute("message", "Profile updated successfully!");
                }
            } else if ("create".equals(action)) {
                if (user == null) {
                    user = new User();
                    user.setFullName(fullName.isEmpty() ? null : fullName);
                    user.setPhone(phone.isEmpty() ? null : phone);
                    user.setEmail(email.isEmpty() ? null : email);
                    if (!fileName.isEmpty()) {
                        user.setImagePath(fileName);
                    }
                    userDAO.create(user);
                    System.out.println("Created user with ID: " + user.getId());
                    request.setAttribute("message", "User created successfully!");
                }
            } else if ("delete".equals(action)) {
                if (user != null) {
                    userDAO.delete(user.getId());
                    user = null;
                    request.setAttribute("message", "User deleted successfully!");
                }
            } else if ("search".equals(action)) {
                if (user != null) {
                    request.setAttribute("message", "User found!");
                } else {
                    request.setAttribute("message", "User not found!");
                }
            }
            request.setAttribute("user", user);
        } catch (Exception e) {
            request.setAttribute("error", "Error processing request: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
    }

}
