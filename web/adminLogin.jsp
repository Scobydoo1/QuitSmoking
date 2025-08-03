<%-- 
    Document   : adminLogin
    Created on : Jul 6, 2025, 4:09:18 PM
    Author     : Nguyen Tien Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Login</title>
        <%@include file="information/bootstrap.jspf" %>
        <link rel="stylesheet" href="css/adminLogin.css">
    </head>
    <body style="background: #d3d3d3">
        <div class="login-container">
            <div class="info-page">
                <h2>Hệ thống Quản lý Cai nghiện Ma túy</h2>
                <p>Chào mừng bạn đến với trang quản trị. Vui lòng đăng nhập để tiếp tục.</p>
            </div>

            <!--Form Login-->
            <div class="login-panel">
                <div class="logo">
                    <img src="images/avata/avatar.jpg" alt="Logo">
                </div>
                <h3 class="inner-title">Admin Login</h3>
                <form action="AdminLoginServlet" method="post">
                    <div class="form-group">
                        <label for="username" class="form-label">Tên đăng nhập</label>
                        <input type="text" class="form-input form-control" id="username" name="username"
                               placeholder="Nhập tên đăng nhập" required>
                    </div>
                    <div class="form-group">
                        <label for="password" class="form-label">Mật khẩu</label>
                        <input type="password" class="form-input form-control" id="password" name="password"
                               placeholder="Nhập mật khẩu" required>
                    </div>
                    <% if (request.getAttribute("error") != null) {%>
                    <div class="alert alert-danger mt-2">
                        <%= request.getAttribute("error")%>
                    </div>
                    <% }%>
                    <div class="form-group">
                        <button type="submit" class="btn-login">Đăng nhập</button>
                    </div>
                    <div class="text-center mt-3">
                        <a href="#" class="forgot-password">Quên mật khẩu?</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
