<%@page import="DTO.BlogPost"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Blog</title>
    <link rel="stylesheet" href="css/myblog.css">
    
</head>
<body>
    <a href="homepage.jsp" class="home-button">Home</a> 
    <div class="container">
        <h2>Your Blog Posts</h2>

        <%
            List<BlogPost> posts = (List<BlogPost>) request.getAttribute("posts");

            if (posts != null && !posts.isEmpty()) {
                for (BlogPost post : posts) {
        %>
            <div class="post">
                <h3><%= post.getTitle() %></h3>
                <p><%= post.getContent() %></p>
                <img src="images/Blog/<%= post.getImage() %>" alt="Image of <%= post.getTitle() %>" />
                <p><strong>Published on:</strong> <%= post.getPublishDate() %></p>
                
                <a href="UpdatePostServlet?idPost=<%= post.getIdPost() %>" class="btn btn-warning">Update</a>
                <a href="DeletePostServlet?idPost=<%= post.getIdPost() %>" class="btn btn-danger"
                   onclick="return confirm('Are you sure you want to delete this post?')">Delete</a>
            </div>
        <%
                } // end for
            } else {
        %>
            <p>You have no posts yet.</p>
        <%
            }
        %>
    </div>
</body>
</html>
