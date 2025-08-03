<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, DTO.Notification" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông báo của bạn</title>
    <link rel="stylesheet" href="css/notification-sidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <%@include file="information/bootstrap.jspf" %>
    <style>
        .notifications-page {
            max-width: 800px;
            margin: 40px auto;
            padding: 0 20px;
        }
        
        .page-header {
            background: #667eea;
            color: white;
            padding: 30px;
            border-radius: 12px;
            margin-bottom: 30px;
            text-align: center;
        }
        
        .page-header h1 {
            margin: 0;
            font-size: 28px;
            font-weight: 600;
        }
        
        .notifications-container {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        
        .back-button {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            background: #667eea;
            color: white;
            padding: 12px 20px;
            border-radius: 8px;
            text-decoration: none;
            margin-bottom: 20px;
            transition: all 0.3s ease;
        }
        
        .back-button:hover {
            background: #764ba2;
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="notifications-page">
        <a href="homepage.jsp" class="back-button">
            <i class="fas fa-arrow-left"></i>
            Quay lại trang chủ
        </a>
        
        <div class="page-header">
            <h1><i class="fas fa-bell"></i> Thông báo của bạn</h1>
        </div>

        <div class="notifications-container">
            <%
                List notifications = (List) request.getAttribute("notifications");
                if (notifications != null && !notifications.isEmpty()) {
                    for (int i = 0; i < notifications.size(); i++) {
                        Notification n = (Notification) notifications.get(i);
            %>
                <div class="notification-item <%= n.isRead() ? "read" : "unread" %>">
                    <div class="notification-icon">
                        <i class="<%= n.getTypeIcon() %>" style="color: <%= n.getTypeColor() %>; font-size: 24px;"></i>
                    </div>
                    <div class="notification-content">
                        <div class="notification-message"><%= n.getMessage() %></div>
                        <div class="notification-date">
                            <i class="fas fa-calendar-alt"></i>
                            <%= n.getFormattedDate() %>
                        </div>
                    </div>
                </div>
            <%
                    }
                } else {
            %>
                <div class="no-notifications">
                    <i class="fas fa-bell-slash"></i>
                    <h3>Không có thông báo nào</h3>
                    <p>Bạn chưa có thông báo nào.</p>
                </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
