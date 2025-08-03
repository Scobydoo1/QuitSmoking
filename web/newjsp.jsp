<%-- 
    Document   : schedulesetup
    Created on : Jun 18, 2025, 7:42:57 PM
    Author     : Nguyen Tien Dat
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xếp lịch học - Health Center</title>
    <link rel="stylesheet" href="css/schedule-setup.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <%
        String idMember = request.getParameter("idMember");
        Boolean hasScheduleObj = (Boolean) request.getAttribute("hasSchedule");
        boolean hasSchedule = hasScheduleObj != null && hasScheduleObj;
        String errorMessage = (String) request.getAttribute("errorMessage");
    %>

    <div class="schedule-container">
        <!-- Header Section -->
        <div class="schedule-header">
            <div class="header-content">
                <div class="header-icon">
                    <i class="fas fa-calendar-plus"></i>
                </div>
                <div class="header-text">
                    <h1 class="page-title">Xếp lịch học cho thành viên</h1>
                    <p class="member-id">
                        <i class="fas fa-user"></i>
                        ID: <span class="member-badge"><%= idMember %></span>
                    </p>
                </div>
                <div class="header-actions">
                    <a href="homepage.jsp" class="btn btn-home">
                        <i class="fas fa-home"></i>
                        Về trang chủ
                    </a>
                </div>
            </div>
        </div>

        <!-- Main Content -->
        <div class="schedule-content">
            <% if (hasSchedule) { %>
                <!-- Already Has Schedule -->
                <div class="alert-card warning">
                    <div class="alert-icon">
                        <i class="fas fa-exclamation-triangle"></i>
                    </div>
                    <div class="alert-content">
                        <h3>Lịch học đã tồn tại</h3>
                        <p>Thành viên này đã có lịch học trong hệ thống.</p>
                        <div class="alert-actions">
                            <a href="ViewScheduleServlet?idMember=<%= idMember %>" class="btn btn-primary">
                                <i class="fas fa-eye"></i>
                                Xem lịch hiện tại
                            </a>
                            <a href="managemembers.jsp" class="btn btn-secondary">
                                <i class="fas fa-arrow-left"></i>
                                Quay lại
                            </a>
                        </div>
                    </div>
                </div>
            <% } else { %>
                <!-- Schedule Setup Form -->
                <div class="form-card">
                    <div class="form-header">
                        <h3>
                            <i class="fas fa-calendar-alt"></i>
                            Thiết lập lịch học mới
                        </h3>
                        <p>Vui lòng điền thông tin để tạo lịch học cho thành viên</p>
                    </div>

                    <form action="CreateScheduleServlet" method="post" id="scheduleForm" class="schedule-form">
                        <input type="hidden" name="idMember" value="<%= idMember %>">

                        <!-- Start Date Section -->
                        <div class="form-section">
                            <div class="section-header">
                                <i class="fas fa-calendar-day"></i>
                                <h4>Ngày bắt đầu</h4>
                            </div>
                            <div class="form-group">
                                <label for="startDate" class="form-label">
                                    Chọn ngày bắt đầu (phải là Thứ 2)
                                </label>
                                <div class="date-input-container">
                                    <input type="date" id="startDate" name="startDate" class="form-input" required>
                                    <div class="input-icon">
                                        <i class="fas fa-calendar"></i>
                                    </div>
                                </div>
                                <div class="form-note">
                                    <i class="fas fa-info-circle"></i>
                                    Lưu ý: Ngày bắt đầu phải là thứ Hai để đảm bảo lịch học đúng quy định
                                </div>
                            </div>
                        </div>

                        <!-- Days Selection Section -->
                        <div class="form-section">
                            <div class="section-header">
                                <i class="fas fa-calendar-week"></i>
                                <h4>Lịch học trong tuần</h4>
                            </div>
                            <div class="form-group">
                                <label class="form-label">Chọn lịch học (3 buổi/tuần)</label>
                                <div class="radio-group">
                                    <label class="radio-option">
                                        <input type="radio" name="days" value="246" checked>
                                        <span class="radio-custom"></span>
                                        <div class="radio-content">
                                            <span class="radio-title">Thứ 2 - 4 - 6</span>
                                            <span class="radio-subtitle">Lịch học đều đặn trong tuần</span>
                                            <div class="day-badges">
                                                <span class="day-badge">T2</span>
                                                <span class="day-badge">T4</span>
                                                <span class="day-badge">T6</span>
                                            </div>
                                        </div>
                                    </label>
                                    
                                    <label class="radio-option">
                                        <input type="radio" name="days" value="357">
                                        <span class="radio-custom"></span>
                                        <div class="radio-content">
                                            <span class="radio-title">Thứ 3 - 5 - 7</span>
                                            <span class="radio-subtitle">Lịch học linh hoạt cuối tuần</span>
                                            <div class="day-badges">
                                                <span class="day-badge">T3</span>
                                                <span class="day-badge">T5</span>
                                                <span class="day-badge">T7</span>
                                            </div>
                                        </div>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <!-- Time Selection Section -->
                        <div class="form-section">
                            <div class="section-header">
                                <i class="fas fa-clock"></i>
                                <h4>Ca học</h4>
                            </div>
                            <div class="form-group">
                                <label for="startTime" class="form-label">
                                    Chọn ca học mỗi buổi (mỗi ca kéo dài 2 giờ)
                                </label>
                                <div class="select-container">
                                    <select id="startTime" name="startTime" class="form-select" required>
                                        <option value="">-- Chọn ca học --</option>
                                        <option value="07:00:00">Ca sáng sớm: 7:00 - 9:00</option>
                                        <option value="09:15:00">Ca sáng: 9:15 - 11:15</option>
                                        <option value="13:00:00">Ca chiều: 13:00 - 15:00</option>
                                        <option value="15:15:00">Ca chiều muộn: 15:15 - 17:15</option>
                                    </select>
                                    <div class="select-icon">
                                        <i class="fas fa-chevron-down"></i>
                                    </div>
                                </div>
                                <div class="time-preview" id="timePreview">
                                    <i class="fas fa-clock"></i>
                                    <span>Chọn ca học để xem thời gian chi tiết</span>
                                </div>
                            </div>
                        </div>

                        <!-- Submit Section -->
                        <div class="form-actions">
                            <button type="button" class="btn btn-secondary" onclick="history.back()">
                                <i class="fas fa-arrow-left"></i>
                                Quay lại
                            </button>
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-calendar-plus"></i>
                                Tạo lịch học
                            </button>
                        </div>
                    </form>
                </div>
            <% } %>

            <!-- Error Message -->
            <% if (errorMessage != null) { %>
                <div class="alert-card error">
                    <div class="alert-icon">
                        <i class="fas fa-exclamation-circle"></i>
                    </div>
                    <div class="alert-content">
                        <h3>Có lỗi xảy ra</h3>
                        <p><%= errorMessage %></p>
                    </div>
                </div>
            <% } %>
        </div>

        <!-- Schedule Info -->
        <div class="info-section">
            <div class="info-card">
                <div class="info-header">
                    <i class="fas fa-info-circle"></i>
                    <h4>Thông tin quan trọng</h4>
                </div>
                <div class="info-content">
                    <div class="info-item">
                        <i class="fas fa-check-circle"></i>
                        <span>Mỗi buổi học kéo dài 2 giờ</span>
                    </div>
                    <div class="info-item">
                        <i class="fas fa-check-circle"></i>
                        <span>Lịch học 3 buổi mỗi tuần</span>
                    </div>
                    <div class="info-item">
                        <i class="fas fa-check-circle"></i>
                        <span>Ngày bắt đầu phải là thứ Hai</span>
                    </div>
                    <div class="info-item">
                        <i class="fas fa-check-circle"></i>
                        <span>Có thể điều chỉnh lịch sau khi tạo</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="js/schedule-setup.js"></script>
</body>
</html>