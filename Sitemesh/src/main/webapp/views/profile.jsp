<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
    <h2>My Profile</h2>
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${user.id}">

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" value="${user.email}" readonly>
        </div>
        <div class="mb-3">
            <label for="fullName" class="form-label">Full Name</label>
            <input type="text" class="form-control" id="fullName" name="fullName" value="${user.fullName}">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone Number</label>
            <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}">
        </div>
        <div class="mb-3">
            <label for="image" class="form-label">Profile Image</label>
            <input class="form-control" type="file" id="image" name="image">
            <c:if test="${not empty user.imagePath}">
                <img src="${pageContext.request.contextPath}/uploads/${user.imagePath}" 
                     alt="Current Profile Image" width="100" class="mt-2">
            </c:if>
        </div>

        <button type="submit" class="btn btn-primary">Update Profile</button>
    </form>
</body>
</html>