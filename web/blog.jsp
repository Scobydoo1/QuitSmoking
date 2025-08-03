<%@page import="DTO.Member"%>
<%@page import="DTO.BlogPost"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog - Quit Smoking Center</title>
    
    <%@include file="information/bootstrap.jspf" %>
    <link rel="stylesheet" href="css/stylehomepage.css">
    <link rel="stylesheet" href="css/blogPageStyle.css">

</head>
<body>

    <%@include file="information/header.jspf" %>

    <!-- Banner Section -->
    <div class="background">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="inner-wrap">
                        <p class="inner-sub-title">HÀNH TRÌNH BỎ THUỐC, LẤY LẠI SỨC KHỎE</p>
                        <h1 class="inner-title">Tin Tức</h1>
                        <div class="inner-blog">
                            <div class="home-page">
                                <%
                                    String userRole = (String) session.getAttribute("role");
                                    if ("member".equals(userRole)) {
                                %>
                                <a href="PostNewBlog.jsp">Đăng Bài</a>
                                <a href="MyBlogServlet">Bài của tôi</a>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Main Blog Section -->
    <section class="blog-section">
        <div class="blog-container">
            <!-- Blog List -->
            <div class="blog-list-container">
                <%
                    List<BlogPost> blogPosts = (List<BlogPost>) request.getAttribute("blogPosts");
                    List<Member> members = (List<Member>) request.getAttribute("members");

                    if (blogPosts != null && !blogPosts.isEmpty()) {
                        for (BlogPost post : blogPosts) {
                            String memberName = "Admin";
                            String avatar = "images/avata/admin_avatar.png"; // Default avatar
                            if (members != null) {
                                for (Member m : members) {
                                    if (m.getIDMember().equals(post.getIdMember())) {
                                        memberName = m.getMemberName();
                                        avatar = (m.getImage() != null && !m.getImage().isEmpty()) ? m.getImage() : "images/avata/nullavata.png";
                                        break;
                                    }
                                }
                            }
                            
                            String content = post.getContent();
                            String excerpt = (content != null && content.length() > 150) ? content.substring(0, 150) + "..." : content;
                %>
                <div class="blog-list-item">
                    <div class="blog-list-item-img">
                        <a href="BlogDetailServlet?id=<%= post.getIdPost() %>">
                            <img src="images/Blog/<%= post.getImage() %>" alt="<%= post.getTitle() %>">
                        </a>
                    </div>
                    <div class="blog-list-item-content">
                        <div class="blog-list-item-meta">
                            <img src="<%= avatar %>" alt="Author" class="author-avatar">
                            <span><strong><%= memberName %></strong> &bull; <%= new java.text.SimpleDateFormat("MMM dd, yyyy").format(post.getPublishDate()) %></span>
                        </div>
                        <a href="BlogDetailServlet?id=<%= post.getIdPost() %>" class="blog-list-item-title"><%= post.getTitle() %></a>
                        <p class="blog-excerpt"><%= excerpt %></p>
                        <div class="blog-list-item-footer">
                            
                            
                        </div>
                    </div>
                </div>
                <%
                        }
                    } else {
                %>
                <div class="col-12">
                    <p class="text-center">Không có bài viết nào được tìm thấy.</p>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </section>

    <%@include file="information/footer.jspf" %>

</body>
</html>
