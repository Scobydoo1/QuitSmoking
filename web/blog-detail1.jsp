<%@page import="DTO.BlogPost"%>
<%@page import="DTO.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chi tiết bài viết</title>
    
    <!-- Bootstrap and Font Awesome -->
    <%@include file="information/bootstrap.jspf" %>
    
    <!-- New Blog Stylesheet -->
    <link rel="stylesheet" href="css/blogPageStyle.css">
    <link rel="stylesheet" href="css/stylehomepage.css">

    <%
        BlogPost post = (BlogPost) request.getAttribute("post");
        Member author = (Member) request.getAttribute("author");
        if (post == null) {
            // Redirect or show error if post is not found
            response.sendRedirect("blog.jsp");
            return;
        }
    %>
</head>
<body>

    <!-- Header -->
    <%@include file="information/header.jspf" %>

    <!-- Main Blog Detail Section -->
    <div class="blog-section">
        <div class="blog-detail-container">
            
            <!-- Post Title -->
            <h1 class="detail-title"><%= post.getTitle() %></h1>
            
            <!-- Post Metadata -->
            <div class="detail-meta">
                <span>
                    <i class="fas fa-user"></i> 
                    Người đăng: <strong><%= (author != null ? author.getMemberName() : "Không rõ") %></strong>
                </span>
                <span>
                    <i class="fas fa-calendar-alt"></i> 
                    Ngày đăng: <strong><%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(post.getPublishDate()) %></strong>
                </span>
            </div>
            
            <!-- Post Image -->
            <img src="images/Blog/<%= post.getImage() %>" class="detail-hero-image" alt="<%= post.getTitle() %>">
            
            <!-- Post Content -->
            <div class="detail-content">
                <p><%= post.getContent().replace("\n", "<br>") %></p>
                <!-- You can add more content paragraphs here if needed -->
            </div>
            
            <!-- Action Buttons -->
            <div class="detail-actions">
                <a href="BlogPostServlet" class="btn-back">
                    <i class="fas fa-arrow-left"></i> Quay lại
                </a>
                <form action="report.jsp" method="get" style="margin: 0;">
                    <input type="hidden" name="type" value="blog">
                    <input type="hidden" name="postId" value="<%= post.getIdPost() %>">
                    <button type="submit" class="btn-report">
                        <i class="fas fa-flag"></i> Báo cáo
                    </button>
                </form>
            </div>
            
        </div>
    </div>

    <!-- Footer -->
    <%@include file="information/footer.jspf" %>

</body>
</html>