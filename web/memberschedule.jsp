<%-- 
    Document   : memberschedule
    Created on : Jun 19, 2025, 12:16:29 AM
    Author     : Nguyen Tien Dat
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, DTO.Schedule" %>
<%
    List<Schedule> scheduleList = (List<Schedule>) request.getAttribute("scheduleList");
    int selectedMonth = (request.getAttribute("selectedMonth") != null)
        ? (int) request.getAttribute("selectedMonth")
        : java.time.LocalDate.now().getMonthValue();
    int selectedYear = (request.getAttribute("selectedYear") != null)
        ? (int) request.getAttribute("selectedYear")
        : java.time.LocalDate.now().getYear();
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lịch học của tôi - Quit Smoking App</title>
    <link rel="stylesheet" href="css/memberschedule.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <!-- HEADER SECTION - Phần tiêu đề trang -->
    <div class="header-container">
        <div class="header-content">
            <div class="header-icon">
                <i class="fas fa-calendar-alt"></i>
            </div>
            <div class="header-text">
                <h1>Lịch học của tôi</h1>
                <p>Quản lý và theo dõi lịch học cai thuốc</p>
            </div>
        </div>
    </div>

    <!-- MAIN CONTAINER - Container chính -->
    <div class="main-container">
        
        <!-- FILTER SECTION - Phần lọc tháng năm -->
        <div class="filter-section">
            <div class="filter-header">
                <h2>
                    <i class="fas fa-calendar-check"></i>
                    Lịch học tháng <%= selectedMonth %>/<%= selectedYear %>
                </h2>
            </div>
            
            <!-- FILTER FORM - Form chọn tháng năm -->
            <div class="filter-form-container">
                <form action="MemberScheduleServlet" method="get" class="filter-form">
                    <div class="form-group">
                        <label for="month-select">
                            <i class="fas fa-calendar"></i>
                            Chọn tháng:
                        </label>
                        <select name="month" id="month-select" class="form-select">
                            <% for (int i = 1; i <= 12; i++) { %>
                                <option value="<%= i %>" <%= (i == selectedMonth ? "selected" : "") %>>
                                    Tháng <%= i %>
                                </option>
                            <% } %>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="year-input">
                            <i class="fas fa-calendar-week"></i>
                            Năm:
                        </label>
                        <input type="number" 
                               name="year" 
                               id="year-input"
                               value="<%= selectedYear %>" 
                               min="2020" 
                               max="2040" 
                               class="form-input" />
                    </div>
                    
                    <div class="form-group">
                        <button type="submit" class="submit-btn">
                            <i class="fas fa-search"></i>
                            <span>Xem lịch</span>
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <!-- SCHEDULE CONTENT - Nội dung lịch học -->
        <div class="schedule-content">
            <% if (scheduleList != null && !scheduleList.isEmpty()) { %>
                <!-- SCHEDULE STATS - Thống kê lịch học -->
                <div class="schedule-stats">
                    <div class="stat-item">
                        <div class="stat-number"><%= scheduleList.size() %></div>
                        <div class="stat-label">Buổi học</div>
                    </div>
                    <div class="stat-divider"></div>
                    <div class="stat-item">
                        <div class="stat-icon">
                            <i class="fas fa-graduation-cap"></i>
                        </div>
                        <div class="stat-label">Lịch học</div>
                    </div>
                </div>

                <!-- SCHEDULE TIMELINE - Dòng thời gian lịch học -->
                <div class="schedule-timeline">
                    <%
                        String currentDate = "";
                        int sessionCount = 0;
                        for (Schedule s : scheduleList) {
                            String sessionDate = s.getSessionDate().toString();
                            if (!sessionDate.equals(currentDate)) {
                                currentDate = sessionDate;
                                sessionCount = 0;
                    %>
                                <!-- DATE HEADER - Tiêu đề ngày -->
                                <div class="date-header">
                                    <div class="date-icon">
                                        <i class="fas fa-calendar-day"></i>
                                    </div>
                                    <div class="date-info">
                                        <h3>Ngày <%= sessionDate %></h3>
                                        <p>Lịch học trong ngày</p>
                                    </div>
                                </div>
                    <%      } 
                            sessionCount++;
                    %>
                            <!-- SCHEDULE CARD - Thẻ lịch học -->
                            <div class="schedule-card">
                                <!-- Card Header - Phần đầu thẻ -->
                                <div class="card-header">
                                    <div class="session-info">
                                        <i class="fas fa-clock"></i>
                                        <span>Buổi <%= sessionCount %></span>
                                    </div>
                                    <div class="status-badge status-<%= s.getStatus().toLowerCase().replace(" ", "-") %>">
                                        <%= s.getStatus() %>
                                    </div>
                                </div>
                                
                                <!-- Card Content - Nội dung thẻ -->
                                <div class="card-content">
                                    <!-- Time Info - Thông tin thời gian -->
                                    <div class="info-row">
                                        <div class="info-label">
                                            <i class="fas fa-hourglass-start"></i>
                                            <span>Thời gian</span>
                                        </div>
                                        <div class="info-value time-value">
                                            <%= s.getStartTime() %> - <%= s.getEndTime() %>
                                        </div>
                                    </div>
                                    
                                    <!-- Room Info - Thông tin phòng học -->
                                    <div class="info-row">
                                        <div class="info-label">
                                            <i class="fas fa-door-open"></i>
                                            <span>Phòng học</span>
                                        </div>
                                        <div class="info-value">
                                            <% if (s.getMeetLink() != null && !s.getMeetLink().isEmpty()) { %>
                                                <a href="<%= s.getMeetLink() %>" 
                                                   target="_blank" 
                                                   class="meet-link">
                                                    <i class="fas fa-video"></i>
                                                    <span>Vào lớp học</span>
                                                    <i class="fas fa-external-link-alt"></i>
                                                </a>
                                            <% } else { %>
                                                <div class="no-room">
                                                    <i class="fas fa-exclamation-triangle"></i>
                                                    <span>Chưa có phòng học</span>
                                                </div>
                                            <% } %>
                                        </div>
                                    </div>
                                </div>
                                
                                <!-- Card Footer - Phần cuối thẻ -->
                                <div class="card-footer">
                                    <div class="footer-note">
                                        <i class="fas fa-info-circle"></i>
                                        <span>Hãy tham gia đúng giờ để có hiệu quả tốt nhất</span>
                                    </div>
                                </div>
                            </div>
                    <%  } %>
                </div>
            <% } else { %>
                <!-- EMPTY STATE - Trạng thái không có lịch -->
                <div class="empty-state">
                    <div class="empty-icon">
                        <i class="fas fa-calendar-times"></i>
                    </div>
                    <h3>Không có lịch học</h3>
                    <p>Không có lịch học nào trong tháng <%= selectedMonth %>/<%= selectedYear %></p>
                    <button onclick="selectCurrentMonth()" class="current-month-btn">
                        <i class="fas fa-calendar-day"></i>
                        <span>Xem tháng hiện tại</span>
                    </button>
                </div>
            <% } %>
        </div>

        <!-- NAVIGATION SECTION - Phần điều hướng -->
        <div class="navigation-section">
            <a href="homepage.jsp" class="nav-btn back-btn">
                <i class="fas fa-home"></i>
                <span>Trang chủ</span>
            </a>
            
            <a href="progressList.jsp" class="nav-btn progress-btn">
                <i class="fas fa-chart-line"></i>
                <span>Tiến trình</span>
            </a>
            
            <a href="BadgeServlet" class="nav-btn badge-btn">
                <i class="fas fa-medal"></i>
                <span>Huy hiệu</span>
            </a>
        </div>
    </div>

    <!-- FLOATING ACTION BUTTON - Nút cuộn lên đầu -->
    <div class="fab-container">
        <button class="fab" onclick="scrollToTop()">
            <i class="fas fa-chevron-up"></i>
        </button>
    </div>

    <script>
        // Cuộn lên đầu trang
        function scrollToTop() {
            window.scrollTo({
                top: 0,
                behavior: 'smooth'
            });
        }

        // Chọn tháng hiện tại
        function selectCurrentMonth() {
            const now = new Date();
            document.getElementById('month-select').value = now.getMonth() + 1;
            document.getElementById('year-input').value = now.getFullYear();
            document.querySelector('.filter-form').submit();
        }

        // Hiển thị/ẩn FAB khi cuộn
        window.addEventListener('scroll', function() {
            const fab = document.querySelector('.fab-container');
            if (window.scrollY > 300) {
                fab.style.opacity = '1';
                fab.style.visibility = 'visible';
            } else {
                fab.style.opacity = '0';
                fab.style.visibility = 'hidden';
            }
        });

        // Animation cho các thẻ lịch học
        document.addEventListener('DOMContentLoaded', function() {
            const scheduleCards = document.querySelectorAll('.schedule-card');
            scheduleCards.forEach((card, index) => {
                card.style.animationDelay = (index * 0.1) + 's';
            });
        });
    </script>
</body>
</html>