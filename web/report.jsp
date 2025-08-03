<%-- 
    Document   : report
    Created on : Jul 13, 2025, 1:55:27 PM
    Author     : Nguyen Tien Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String reporterID = (String) session.getAttribute("id");
    if (reporterID == null) {
        reporterID = "không có";
    }

    String role = (String) session.getAttribute("role");
    if (role == null) {
        role = "guest";
    }

    String type = request.getParameter("type");
    if (type == null) {
        type = "system";
    }

    String postId = request.getParameter("postId");
    String link = "";
    if ("blog".equals(type) && postId != null && !postId.isEmpty()) {
        link = "http://localhost:8080/PageQuitSmoking/BlogDetailServlet?id=" + postId;
    }
%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Gửi báo cáo</title>
        <link rel="stylesheet" href="css/report.css">


    </head>
    <body>
        <a href="homepage.jsp" class="home-button">Home</a>

        <h2>📣 Gửi báo cáo - <%= ("system".equals(type) ? "Hệ thống" : "Bài viết")%></h2>

        <% String message = (String) request.getAttribute("message");
            if (message != null) {%>
        <p style="color: green;"><%= message%></p>
        <% }%>

        <form action="ReportServlet" method="post">
            <input type="hidden" name="reporterID" value="<%= reporterID%>">
            <input type="hidden" name="role" value="<%= role%>">
            <input type="hidden" name="type" value="<%= type%>">

            <label for="reportType">Loại báo cáo:</label>
            <select name="reportType" id="reportType" required>
                <% if ("system".equals(type)) { %>
                <option value="Lỗi tải trang">Lỗi tải trang</option>
                <option value="Lỗi phông chữ">Lỗi phông chữ</option>
                <option value="Không xem được ảnh">Không xem được ảnh</option>
                <option value="Nút bấm không được">Nút bấm không được</option>
                <option value="Khác">Khác</option>
                <% } else { %>
                <option value="Quấy rối, lăng mạ, ngược đãi">Quấy rối, lăng mạ, ngược đãi</option>
                <option value="Tự tử, gây thương tích, hình ảnh bạo lực">Tự tử, gây thương tích, hình ảnh bạo lực</option>
                <option value="Chia sẽ, buôn bán hàng cấm">Chia sẽ, buôn bán hàng cấm</option>
                <option value="Nội dung người lớn">Nội dung người lớn</option>
                <option value="Thông tin sai sự thật">Thông tin sai sự thật</option>
                <option value="Khác">Khác</option>
                <% } %>
            </select>

            <label for="link">Link liên quan:</label>
            <% if ("blog".equals(type)) {%>
            <input type="text" name="link" id="link" value="<%= link%>" readonly>
            <% } else { %>
            <input type="text" name="link" id="link">
            <% }%>

            <label for="description">Mô tả:</label>
            <textarea name="description" id="description" rows="5" required></textarea>

            <input type="submit" value="Gửi báo cáo">
        </form>

    </body>
</html>
