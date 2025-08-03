<%-- 
    Document   : managemembers
    Created on : Jun 18, 2025, 6:51:28 PM
    Author     : Nguyen Tien Dat
--%>
<%@page import="DAO.ScheduleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, DTO.Member" %>
<%
    List<Member> memberList = (List<Member>) request.getAttribute("memberList");
%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quản lý thành viên - Health Center</title>
        <link rel="stylesheet" href="css/manage-members.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>
    <body>
        <div class="page-container">
            <!-- Header Section -->
            <div class="page-header">
                <div class="header-content">
                    <div class="header-left">
                        <h1 class="page-title">
                            <i class="fas fa-users"></i>
                            Danh sách thành viên
                        </h1>
                        <p class="page-subtitle">Quản lý và theo dõi tiến trình của các thành viên</p>
                    </div>
                    <div class="header-actions">
                        <a href="homepage.jsp" class="btn btn-home">
                            <i class="fas fa-home"></i>
                            Về trang chủ
                        </a>
                        <button class="btn btn-secondary" onclick="refreshData()">
                            <i class="fas fa-sync-alt"></i>
                            Làm mới
                        </button>
                        <button class="btn btn-primary" onclick="exportData()">
                            <i class="fas fa-download"></i>
                            Xuất dữ liệu
                        </button>
                    </div>
                </div>
            </div>

            <!-- Stats Cards -->
            <div class="stats-section">
                <div class="stats-grid">
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-users"></i>
                        </div>
                        <div class="stat-content">
                            <span class="stat-number"><%= memberList != null ? memberList.size() : 0%></span>
                            <span class="stat-label">Tổng thành viên</span>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-user-check"></i>
                        </div>
                        <div class="stat-content">
                            <span class="stat-number">
                                <%
                                    int activeCount = 0;
                                    if (memberList != null) {
                                        for (Member m : memberList) {
                                            if ("active".equalsIgnoreCase(m.getStatus())) {
                                                activeCount++;
                                            }
                                        }
                                    }
                                %>
                                <%= activeCount%>
                            </span>
                            <span class="stat-label">Đang hoạt động</span>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-chart-line"></i>
                        </div>
                        <div class="stat-content">
                            <span class="stat-number">85%</span>
                            <span class="stat-label">Tỷ lệ thành công</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Search and Filter Section -->
            <div class="search-section">
                <div class="search-container">
                    <div class="search-box">
                        <i class="fas fa-search"></i>
                        <input type="text" id="searchInput" placeholder="Tìm kiếm theo tên, email, ID..." onkeyup="searchMembers()">
                    </div>
<!--                    <div class="filter-buttons">
                        <button class="filter-btn active" data-filter="all" onclick="filterMembers('all')">
                            Tất cả
                        </button>
                        <button class="filter-btn" data-filter="active" onclick="filterMembers('active')">
                            Hoạt động
                        </button>
                        <button class="filter-btn" data-filter="inactive" onclick="filterMembers('inactive')">
                            Không hoạt động
                        </button>
                    </div>-->
                </div>
            </div>

            <!-- Members Table -->
            <div class="table-section">
                <div class="table-container">
                    <div class="table-header">
                        <h3>
                            <i class="fas fa-table"></i>
                            Danh sách chi tiết
                        </h3>
                        <div class="table-actions">
                            <button class="btn-icon" title="Sắp xếp">
                                <i class="fas fa-sort"></i>
                            </button>
                            <button class="btn-icon" title="Cài đặt cột">
                                <i class="fas fa-columns"></i>
                            </button>
                        </div>
                    </div>

                    <div class="table-wrapper">
                        <table class="members-table" id="membersTable">
                            <thead>
                                <tr>
                                    <th>
                                        <input type="checkbox" id="selectAll" onchange="toggleSelectAll()">
                                    </th>
                                    <th>ID</th>
                                    <th>Họ tên</th>
                                    <th>Giới tính</th>
                                    <th>Điện thoại</th>
                                    <th>Email</th>
                                    <th>Mức độ</th>
                                    <th>Mô tả của thành viên</th>
                                    <th>Trạng thái</th>
                                    <th>Quản lý</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    if (memberList != null && !memberList.isEmpty()) {
                                        for (Member m : memberList) {
                                            String statusClass = "status-active";
                                            String genderIcon = "male".equalsIgnoreCase(m.getGender()) ? "fas fa-mars" : "fas fa-venus";
                                %>
                                <tr class="member-row" data-status="<%= m.getStatus().toLowerCase()%>">
                                    <td>
                                        <input type="checkbox" class="member-checkbox" value="<%= m.getIDMember()%>">
                                    </td>
                                    <td class="member-id">
                                        <span class="id-badge"><%= m.getIDMember()%></span>
                                    </td>
                                    <td class="member-name">
                                        <div class="name-container">
                                            <div class="avatar">
                                                <i class="fas fa-user"></i>
                                            </div>
                                            <div class="name-info">
                                                <span class="name"><%= m.getMemberName()%></span>
                                                <span class="member-since">Thành viên từ 2024</span>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="member-gender">
                                        <span class="gender-badge">
                                            <i class="<%= genderIcon%>"></i>
                                            <%= m.getGender()%>
                                        </span>
                                    </td>
                                    <td class="member-phone">
                                        <a href="tel:<%= m.getPhone()%>" class="phone-link">
                                            <i class="fas fa-phone"></i>
                                            <%= m.getPhone()%>
                                        </a>
                                    </td>
                                    <td class="member-email">
                                        <a href="mailto:<%= m.getEmail()%>" class="email-link">
                                            <i class="fas fa-envelope"></i>
                                            <%= m.getEmail()%>
                                        </a>
                                    </td>
                                    <td class="member-points">
                                        <div class="points-container">
                                            <span class="points-number"><%= m.getPoint()%></span>
                                            
                                        </div>
                                    </td>
                                    <td class="member-subscription">
                                        <span class="subscription-badge">
                                            <%= m.getSubscription() != null ? m.getSubscription() : "Chưa có"%>
                                        </span>
                                    </td>
<!--                                    <td class="member-package">
                                        <span class="package-badge">
                                            Gói cơ bản
                                        </span>
                                    </td>-->
                                    <td class="member-status">
                                        <span class="status-badge <%= statusClass%>">
                                            <i class="fas fa-circle"></i>
                                            <%  if (m.getStatus().contains("1") ){%>
                                            Đang hoạt động
                                            <% } else {%>
                                            Đã bị xóa bởi admin
                                            <% } %>
                                        </span>
                                    </td>
                                    <td class="member-actions">
                                        <div class="action-buttons">
                                            <form action="ViewProgressServlet" method="get" style="display: inline;">
                                                <input type="hidden" name="id" value="<%= m.getIDMember()%>">
                                                <button type="submit" class="btn-action primary" title="Xem tiến trình">
                                                    <i class="fas fa-chart-line"></i>
                                                    <span>Xem tiến trình</span>
                                                </button>
                                            </form>

                                            <form action="ScheduleSetupServlet" method="get" style="display: inline;">
                                                <input type="hidden" name="idMember" value="<%= m.getIDMember()%>">
                                                <button type="submit" class="btn-action secondary" title="Xếp lịch">
                                                    <i class="fas fa-calendar-plus"></i>
                                                    <span>Xếp lịch</span>
                                                </button>
                                            </form>
                                            <form action="CreateQuestion.jsp"  style="display: inline;">
                                                <input type="hidden" name="idMember" value="<%= m.getIDMember()%>">
                                                <button type="submit" class="btn-action success" title="Tạo kế hoạch">
                                                    <i class="fas fa-clipboard-list"></i>
                                                    <span>Tạo kế hoạch</span>
                                                </button>
                                            </form>
                                            <%
                                                String idCoach = (String) session.getAttribute("id");

                                                boolean showFinish = ScheduleDAO.canFinishCourse(idCoach, m.getIDMember()); %>
                                            <% if (showFinish) {%>
                                            <script>
                                                function confirmFinish() {
                                                    return confirm("Kết thúc khóa học với thành viên này, hãy đảm bảo bạn và thành viên đã hoàn thành đủ nội dung khóa học.");
                                                }
                                            </script>
                                            <form action="FinishCourseServlet" method="post">
                                                <input type="hidden" name="idMember" value="<%= m.getIDMember()%>"/>
                                                <button type="submit"  onclick="return confirmFinish()" class="btn-action secondary" title="Xếp lịch">
                                                    <i class="fas fa-clipboard-list"></i>
                                                    <span>Kết thúc khóa học</span>                                                    
                                                </button>
                                            </form>
                                            <% } %>
                                        </div>
                                    </td>
                                </tr>
                                <%
                                    }
                                } else {
                                %>
                                <tr class="no-data-row">
                                    <td colspan="11" class="no-data">
                                        <div class="no-data-content">
                                            <i class="fas fa-users-slash"></i>
                                            <h3>Không có thành viên nào</h3>
                                            <p>Hiện tại chưa có thành viên nào trong hệ thống.</p>
                                        </div>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>

                    <!-- Pagination -->
                    <div class="pagination-section">
                        <div class="pagination-info">
                            Hiển thị <span class="current-count"><%= memberList != null ? memberList.size() : 0%></span> 
                            trong tổng số <span class="total-count"><%= memberList != null ? memberList.size() : 0%></span> thành viên
                        </div>
                        <div class="pagination-controls">
                            <button class="btn-page" disabled>
                                <i class="fas fa-chevron-left"></i>
                            </button>
                            <button class="btn-page active">1</button>
                            <button class="btn-page">2</button>
                            <button class="btn-page">3</button>
                            <button class="btn-page">
                                <i class="fas fa-chevron-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/manage-members.js"></script>
    </body>
</html>