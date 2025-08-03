
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="DTO.BlogPost" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật bài viết</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/updatePost.css">
</head>
<body>
    <a href="homepage.jsp" class="home-button">Home</a>
<%
    BlogPost post = (BlogPost) request.getAttribute("post");
    if (post == null) {
%>
    <div class="container text-center mt-5">
        <p class="text-danger">Không tìm thấy bài viết để chỉnh sửa.</p>
    </div>
<%
    } else {
%>
    <h2>Chỉnh sửa bài viết</h2>

    <form action="UpdatePostServlet" method="post" enctype="multipart/form-data">

        <!-- Hidden fields -->
        <input type="hidden" name="idMember" value="<%= post.getIdMember() %>"/>
        <input type="hidden" name="IDPost" value="<%= post.getIdPost() %>"/>
        <input type="hidden" name="existingImage" value="<%= post.getImage() %>"/>

        <!-- ID bài viết -->
        <p class="form-group"><strong>ID Bài viết:</strong> <%= post.getIdPost() %></p>

        <!-- Tiêu đề -->
        <p class="form-group">
            <label for="title">Tiêu đề:</label>
            <input type="text" id="title" name="title" value="<%= post.getTitle() %>" required />
        </p>

        <!-- Nội dung -->
        <p class="form-group">
            <label for="content">Nội dung:</label>
            <textarea id="content" name="content" rows="8" required><%= post.getContent() %></textarea>
        </p>

        <!-- Ảnh -->
        <p class="form-group">
            <label>Ảnh hiện tại:</label><br/>
            <img src="images/Blog/<%= post.getImage() %>" width="200" alt="Ảnh bài viết"/><br/><br/>
            <label for="imageFile">Chọn ảnh mới:</label>
            <input type="file" id="imageFile" name="image" accept="image/*" />
        </p>

    
     

        <!-- Buttons -->
        <div class="form-group text-center">
            <button type="submit">Lưu thay đổi</button>
            <a href="MyBlogServlet">Hủy</a>
        </div>
    </form>
<%
    }
%>
</body>

