<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<form action="${pageContext.request.contextPath}/profile" method="post"
		enctype="multipart/form-data">
		<div class="mb-3">
			<label for="id" class="form-label">ID</label> 
			<input type="text"
				name="id" value="${user.id}" >
		</div>


		<div class="mb-3">
			<label for="email" class="form-label">Email</label>
			<!-- >input type="email" class="form-control" id="email" value="${user.email}" readonly -->
			<input type="email" class="form-control" id="email" name="email"
				value="${user.email}">
		</div>
		<div class="mb-3">
			<label for="fullName" class="form-label">Full Name</label> <input
				type="text" class="form-control" id="fullName" name="fullName"
				value="${user.fullName}">
		</div>
		<div class="mb-3">
			<label for="phone" class="form-label">Phone Number</label> <input
				type="text" class="form-control" id="phone" name="phone"
				value="${user.phone}">
		</div>
		<div class="mb-3">
			<label for="image" class="form-label">Profile Image</label> <input
				class="form-control" type="file" id="image" name="image">
			<c:if test="${not empty user.imagePath}">
				<img
					src="${pageContext.request.contextPath}/uploads/${user.imagePath}"
					alt="Current Profile Image" width="100" class="mt-2">
			</c:if>
		</div>

		<div class="mb-3">
			<button type="submit" class="btn btn-primary" name="action"
				value="update">Update Profile</button>
			<button type="submit" class="btn btn-success" name="action"
				value="create">Create</button>
			<button type="submit" class="btn btn-danger" name="action"
				value="delete">Delete</button>
			<button type="submit" class="btn btn-info" name="action"
				value="search">Search</button>
		</div>
	</form>
</body>
</html>