<%-- 
    Document   : adminDashboard
    Created on : Jul 6, 2025, 4:12:15 PM
    Author     : Nguyen Tien Dat
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    }
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Trang Quản trị Admin</title>
        <%@include file="information/bootstrap.jspf" %>
        <link rel="stylesheet" href="css/admin-dasboard.css">
        <style>
            
        </style>
    </head>
    <body>
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
                <li><a href="AdminViewPaymentServlet"><i class="fas fa-registered"></i> Quản lý Đăng ký</a></li>
            </ul>
            <div class="logout">
                <form action="AdminLogoutServlet" method="get">
                    <button type="submit" class="btn-logout">
                        <i class="fas fa-sign-out-alt"></i> Đăng xuất
                    </button>
                </form>
            </div>
        </div>


        <div class="main-content">
            <div class="content-header">
                <h1>Chào mừng ADMIN đấng toàn năng của chúng tôi!!</h1>
                <p>Đây là bảng điều khiển chính của hệ thống quản lý cai nghiện ma túy.</p>
            </div>

            <!--            <div class="dashboard-cards">
                            <a href="ManageCoachServlet" class="card">
                                <i class="fas fa-users"></i>
                                <h4>Quản lý Coach</h4>
                            </a>
                            <a href="AdminManageMemberServlet" class="card">
                                <i class="fas fa-user-friends"></i>
                                <h4>Quản lý Member</h4>
                            </a>
                            <a href="adminManageQuiz.jsp" class="card">
                                <i class="fas fa-question-circle"></i>
                                <h4>Quản lý Quiz</h4>
                            </a>
                            <a href="QuitplanManagerServlet" class="card">
                                <i class="fas fa-chalkboard-teacher"></i>
                                <h4>Quản lý khóa học</h4>
                            </a>
                            <a href="ViewAllReportsServlet" class="card">
                                <i class="fa-solid fa-circle-info"></i>
                                <h4>Xem báo cáo</h4>
                            </a>
                               <a href="AdminBlogViewServlet" class="card">
                                <i class="fa-solid fa-circle-info"></i>
                                <h4>Quản lý Blog </h4>
                            </a>
                             <a href="AdminViewPaymentServlet" class="card">
                                <i class="fa-solid fa-circle-info"></i>
                                <h4>Quản lý đăng ký </h4>
                            </a>
                            
                            
                        </div>-->

            <h2>Tổng số thành viên: <%= (Integer) session.getAttribute("totalMembers")%></h2>
            <h2>Tổng số huấn luyện viên: <%= (Integer) session.getAttribute("totalCoaches")%></h2>
            <h2>Số thành viên đã đăng ký gói cai nghiện: <%= (Integer) session.getAttribute("totalRegisteredMembers")%></h2>
            <%
                Map<String, Integer> memberCountByPlan = (Map<String, Integer>) session.getAttribute("memberCountByPlan");
                if (memberCountByPlan != null) {
            %>
            <h3>Số thành viên theo từng gói cai nghiện:</h3>
            <ul>
                <li>Bạc: <%= memberCountByPlan.getOrDefault("QP01", 0)%> thành viên</li>
                <li>Vàng: <%= memberCountByPlan.getOrDefault("QP02", 0)%> thành viên</li>
                <li>Kim Cương: <%= memberCountByPlan.getOrDefault("QP03", 0)%> thành viên</li>
            </ul>
            <%
                }
            %>
        </div>
    </body>
</html>

