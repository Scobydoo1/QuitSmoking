<%-- 
    Document   : coachschedule.jsp
    Created on : Jun 18, 2025, 9:24:39 PM
    Author     : Nguyen Tien Dat
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, DTO.Schedule" %>
<%
    List<Schedule> scheduleList = (List<Schedule>) request.getAttribute("scheduleList");
    int selectedMonth = (int) request.getAttribute("selectedMonth");
    int selectedYear = (int) request.getAttribute("selectedYear");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lịch dạy của huấn luyện viên - Health Center</title>
    <link rel="stylesheet" href="css/coach-schedule.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="schedule-container">
        <!-- Header Section -->
        <div class="schedule-header">
            <div class="header-content">
                <div class="header-icon">
                    <i class="fas fa-calendar-alt"></i>
                </div>
                <div class="header-text">
                    <h1 class="page-title">Lịch dạy tháng <%= selectedMonth %>/<%= selectedYear %></h1>
                    <p class="page-subtitle">Quản lý lịch dạy và cập nhật trạng thái buổi học</p>
                </div>
                <div class="header-actions">
                    <a href="homepage.jsp" class="btn btn-home">
                        <i class="fas fa-home"></i>
                        Về trang chủ
                    </a>
                </div>
            </div>
        </div>

        <!-- Filter Section -->
        <div class="filter-section">
            <div class="filter-card">
                <div class="filter-header">
                    <i class="fas fa-filter"></i>
                    <h3>Chọn thời gian</h3>
                </div>
                <form action="CoachScheduleServlet" method="get" class="filter-form">
                    <div class="form-group">
                        <label for="month">Chọn tháng:</label>
                        <select name="month" id="month" class="form-select">
                            <% for (int i = 1; i <= 12; i++) { %>
                                <option value="<%= i %>" <%= (i == selectedMonth ? "selected" : "") %>>
                                    Tháng <%= i %>
                                </option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="year">Năm:</label>
                        <input type="number" name="year" id="year" value="<%= selectedYear %>" 
                               min="2020" max="2040" class="form-input" />
                    </div>
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-search"></i>
                        Xem lịch
                    </button>
                </form>
            </div>
        </div>

        <!-- Schedule Content -->
        <div class="schedule-content">
            <% if (scheduleList != null && !scheduleList.isEmpty()) {
                String currentDate = "";
                for (Schedule s : scheduleList) {
                    String sessionDate = s.getSessionDate().toString();
                    if (!sessionDate.equals(currentDate)) {
                        currentDate = sessionDate;
            %>
                        <div class="date-section">
                            <div class="date-header">
                                <i class="fas fa-calendar-day"></i>
                                <h3>Ngày: <%= sessionDate %></h3>
                            </div>
                        </div>
            <%      } %>
                    <div class="session-card">
                        <div class="session-header">
                            <div class="time-info">
                                <i class="fas fa-clock"></i>
                                <span class="time-range">
                                    <%= s.getStartTime() %> - <%= s.getEndTime() %>
                                </span>
                            </div>
                            <div class="status-badge status-<%= s.getStatus().toLowerCase().replace(" ", "-") %>">
                                <i class="fas fa-circle"></i>
                                <%= s.getStatus() %>
                            </div>
                        </div>
                        
                        <div class="session-body">
                            <div class="member-info">
                                <div class="member-avatar">
                                    <i class="fas fa-user"></i>
                                </div>
                                <div class="member-details">
                                    <h4>Thành viên: <%= s.getMemberName() %></h4>
                                    <p>ID: <%= s.getIDMember() %></p>
                                </div>
                            </div>
                            
                            <form action="UpdateStatusServlet" method="post" class="update-form">
                                <input type="hidden" name="scheduleId" value="<%= s.getIDSchedule() %>">
                                
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="status-<%= s.getIDSchedule() %>">Trạng thái:</label>
                                        <select name="status" id="status-<%= s.getIDSchedule() %>" class="form-select">
                                            <option value="Not yet" <%= "Not yet".equals(s.getStatus()) ? "selected" : "" %>>
                                                Chưa học
                                            </option>
                                            <option value="Present" <%= "Present".equals(s.getStatus()) ? "selected" : "" %>>
                                                Có mặt
                                            </option>
                                            <option value="Absent" <%= "Absent".equals(s.getStatus()) ? "selected" : "" %>>
                                                Vắng mặt
                                            </option>
                                        </select>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="meetLink-<%= s.getIDSchedule() %>">Link học:</label>
                                        <input type="url" name="meetLink" id="meetLink-<%= s.getIDSchedule() %>" 
                                               value="<%= s.getMeetLink() != null ? s.getMeetLink() : "" %>" 
                                               class="form-input" placeholder="https://meet.google.com/...">
                                    </div>
                                </div>
                                
                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fas fa-save"></i>
                                        Cập nhật
                                    </button>
                                    <% if (s.getMeetLink() != null && !s.getMeetLink().isEmpty()) { %>
                                        <a href="<%= s.getMeetLink() %>" target="_blank" class="btn btn-secondary">
                                            <i class="fas fa-video"></i>
                                            Vào buổi học
                                        </a>
                                    <% } %>
                                </div>
                            </form>
                        </div>
                    </div>
            <%  }
            } else { %>
                <div class="no-schedule">
                    <div class="no-schedule-icon">
                        <i class="fas fa-calendar-times"></i>
                    </div>
                    <h3>Không có lịch dạy</h3>
                    <p>Không có lịch nào trong tháng <%= selectedMonth %>/<%= selectedYear %>.</p>
                </div>
            <% } %>
        </div>
    </div>

    <script src="js/coach-schedule.js"></script>
</body>
</html>