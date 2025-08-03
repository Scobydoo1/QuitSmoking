<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.QuitPlan" %>

<%-- Check for admin session --%>
<% if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    } %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Tạo mới Quitplan</title>
        <%@include file="information/bootstrap.jspf" %>
        <link rel="stylesheet" href="css/admin-dasboard.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
        </style>
    </head>
    <body>
        <div class="d-flex">
            <!-- Sidebar -->
            <div class="sidebar">
                <div class="sidebar-header">
                    <h3>Admin Manager</h3>
                </div>
                <ul class="sidebar-nav">
                    <li>
                        <a href="ManageCoachServlet">
                            <i class="fas fa-users"></i> Quản lý Coach
                        </a>
                    </li>
                    <li>
                        <a href="AdminManageMemberServlet">
                            <i class="fas fa-user-friends"></i> Quản lý Member
                        </a>
                    </li>
                    <li>
                        <a href="adminManageQuiz.jsp">
                            <i class="fas fa-question-circle"></i> Quản lý Quiz
                        </a>
                    </li>
                    <li>
                        <a href="QuitplanManagerServlet">
                            <i class="fas fa-chalkboard-teacher"></i> Quản lý khóa học
                        </a>
                    </li>
                    <li>
                        <a href="ViewAllReportsServlet">
                            <i class="fa-solid fa-circle-info"></i> Xem báo cáo
                        </a>
                    </li>

                    <li><a href="AdminBlogViewServlet"><i class="fas fa-blog"></i> Quản lý Blog</a></li>
                    <li><a href="RegistrationResult.jsp"><i class="fas fa-registered"></i> Quản lý Đăng ký</a></li>
                </ul>
                <div class="logout">
                    <form action="AdminLogoutServlet" method="get">
                        <button type="submit" class="btn-logout"><i class="fas fa-sign-out-alt"></i> Đăng xuất</button>
                    </form>
                </div>
            </div>
            <!-- /Sidebar -->

            <!-- Main Content -->
            <div class="main-content">
                <div class="container">
                    <div class="card">
                        <div class="card-header bg-success text-white">
                            <h4 class="mb-0">Tạo mới Quitplan</h4>
                        </div>
                        <div class="card-body">
                            <% QuitPlan qp = (QuitPlan) request.getAttribute("qp");
                            String error = (String) request.getAttribute("error");
                            if (error != null) {%>
                            <div class="alert alert-danger"><%= error%></div>
                            <% }%>
                            <form action="AdminCreateQuitplanServlet" method="post">
                                <div class="form-group">
                                    <label for="idQuitPlan">ID QuitPlan</label>
                                    <input type="text" class="form-control" id="idQuitPlan" name="idQuitPlan" value="<%= qp != null ? qp.getIdQuitPlan() : ""%>" required>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="periodOfTime">Thời gian (tháng)</label>
                                        <input type="number" class="form-control" id="periodOfTime" name="periodOfTime" value="<%= qp != null ? qp.getPeriodOfTime() : 0%>" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="price">Giá</label>
                                        <input type="number" step="0.01" class="form-control" id="price" name="price" value="<%= qp != null ? qp.getPrice() : 0.0%>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="goals">Mục tiêu (Goals)</label>
                                    <textarea class="form-control" id="goals" name="goals" rows="3"><%= qp != null ? qp.getGoals() : ""%></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="progress">Tiến độ (Progress)</label>
                                    <textarea class="form-control" id="progress" name="progress" rows="3"><%= qp != null ? qp.getProgress() : ""%></textarea>
                                </div>
                                <button type="submit" class="btn btn-success"><i class="fas fa-plus-circle"></i> Tạo mới</button>
                                <a href="QuitplanManagerServlet" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Hủy</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Main Content -->
        </div>
    </body>
</html>
