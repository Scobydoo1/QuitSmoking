

<%@page import="DAO.MemberDao"%>
<%@page import="DAO.SystemDao"%>
<%@page import="DAO.NotificationDao"%>
<%@page import="DTO.QuitPlan"%>
<%@page import="DTO.Notification"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Center - Homepage</title>
        <link href="css/app-notification-box.css" rel="stylesheet" type="text/css"/>
        <link href="css/stylehomepage.css" rel="stylesheet" type="text/css"/>
        <link href="css/notification-sidebar.css" rel="stylesheet" type="text/css"/>
        <%@include file="information/bootstrap.jspf" %>
    </head>
    <body>
        <%@include file="information/header.jspf" %>

        <!-- Notification Button -->
        <%        String userId = (String) session.getAttribute("id");
            String userRole = (String) session.getAttribute("role");
            List notifications = null;
            int unreadCount = 0;

            if (userId != null && userRole.equalsIgnoreCase("member")) {
                notifications = NotificationDao.getRecentNotifications(userId, 10);
                unreadCount = NotificationDao.getUnreadCount(userId);
            } else if (userId != null && userRole.equalsIgnoreCase("coach")) {
                notifications = NotificationDao.getNotificationsByCoachId(userId);
                unreadCount = NotificationDao.getUnreadCount(userId);
            }


        %>



        <% if (userId != null) { %>

        <div class="notification-button-container">
            <button class="notification-btn" onclick="openNotificationSidebar()" title="Xem thông báo">
                <i class="fas fa-bell"></i>
                <span class="notification-text">Thông báo</span>
                <% if (unreadCount > 0) {%>
                <span class="notification-count"><%= unreadCount%></span>
                <% } %>
            </button>
        </div>
        <% } %>   

        <!-- Notification Sidebar -->
        <div id="notificationSidebar" class="notification-sidebar">
            <div class="sidebar-header">
                <h3><i class="fas fa-bell"></i> Thông báo</h3>
                <div class="sidebar-actions">
                    <button class="mark-all-btn" onclick="markAllAsRead()" title="Đánh dấu tất cả đã đọc">
                        <i class="fas fa-check-double"></i>
                    </button>
                    <button class="close-sidebar-btn" onclick="closeNotificationSidebar()" title="Đóng">
                        <i class="fas fa-times"></i>
                    </button>
                </div>
            </div>

            <div class="sidebar-body">
                <div class="notification-list" id="notificationList">
                    <% if (notifications != null && !notifications.isEmpty()) {
                            for (int i = 0; i < notifications.size(); i++) {
                                Notification n = (Notification) notifications.get(i);
                    %>
                    <div class="notification-item <%= n.isRead() ? "read" : "unread"%>" 
                         data-notification-id="<%= n.getIdNotification()%>">
                        <div class="notification-icon">
                            <i class="<%= n.getTypeIcon()%>" style="color: <%= n.getTypeColor()%>"></i>
                        </div>
                        <div class="notification-content">
                            <div class="notification-message"><%= n.getMessage()%></div>
                            <div class="notification-date">
                                <i class="fas fa-calendar-alt"></i>
                                <%= n.getFormattedDate()%>
                            </div>
                        </div>
                        <% if (!n.isRead()) { %>
                        <div class="unread-dot"></div>
                        <% }%>
                        <button class="delete-notification-btn" 
                                onclick="deleteNotification('<%= n.getIdNotification()%>')"
                                title="Xóa thông báo">
                            <i class="fas fa-trash"></i>
                        </button>
                    </div>
                    <% }
                    } else { %>
                    <div class="no-notifications">
                        <i class="fas fa-bell-slash"></i>
                        <h4>Không có thông báo nào</h4>
                        <p>Bạn chưa có thông báo nào.</p>
                    </div>
                    <% }%>
                </div>
            </div>

            <div class="sidebar-footer">
                <div class="notification-stats">
                    <span class="total-count">Tổng: <%= notifications != null ? notifications.size() : 0%></span>
                    <span class="unread-count">Chưa đọc: <%= unreadCount%></span>
                </div>
            </div>
        </div>

        <div id="notificationOverlay" class="notification-overlay"></div>

        <c:if test="${not empty requestScope.error}">
            <div class="error-message" style="color: red; font-weight: bold;">
                <p>${requestScope.error}</p>
            </div>
        </c:if>

        <!-- Hero Section -->
        <div class="background">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="inner-wrap">
                            <p class="inner-sub-title">HÀNH TRÌNH BỎ THUỐC, LẤY LẠI SỨC KHỎE</p>
                            <h1 class="inner-title">Sống Khỏe Mỗi Ngày</h1>
                            <div class="inner-button">
                                <a href="AboutUs.jsp" class="button-logo">Tìm hiểu thêm</a>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Welcome Section -->
        <div class="welcome">
            <div class="container">
                <div class="row">
                    <div class="col-xl-6 d-flex align-items-center">
                        <div class="inner-main">
                            <h1 class="inner-title">
                                Chào mừng bạn đến với Trung tâm Cai nghiện Thuốc lá!
                            </h1>
                            <p class="inner-desc">
                                Sức khỏe của bạn là ưu tiên hàng đầu của chúng tôi. Chúng tôi tin rằng một đội ngũ chuyên nghiệp – bao gồm bác sĩ, nhân viên y tế và bộ phận hỗ trợ – sẽ mang đến cho bạn sự chăm sóc tốt nhất với tinh thần tôn trọng và cảm thông. Trung tâm luôn nỗ lực không ngừng để cải thiện phương pháp điều trị và nâng cao chất lượng dịch vụ, với mục tiêu giúp bạn từ bỏ thuốc lá một cách an toàn và hiệu quả, hướng tới một cuộc sống khỏe mạnh hơn.
                            </p>
                        </div>
                    </div>
                    <div class="col-xl-6">
                        <div class="inner-image">
                            <img src="images/home/image8.png" alt="Logo">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%
            SystemDao dao = new SystemDao();
            List planList = dao.getAllQuitPlans();
        %>

        <!-- Plan Section -->
        <div class="plan">
            <div class="container">
                <div class="inner-header">
                    <h2 class="inner-title">Các khóa học cai thuốc</h2>
                </div>
                <div class="row">
                    <% for (int i = 0; i < planList.size(); i++) {
                            QuitPlan plan = (QuitPlan) planList.get(i);
                    %>
                    <div class="col-xl-4 d-flex">
                        <div class="inner-plan-one d-flex flex-column w-100">
                            <div class="inner-image">
                                <img src="images/home/plan-1.jpg" alt="anh1">
                            </div>
                            <div class="inner-content flex-grow-1 d-flex flex-column">
                                <h4 class="inner-title"><%= plan.getGoals()%></h4>
                                <p class="inner-desc">
                                    <%= plan.getProgress()%>
                                </p>
                                <%
                                    Boolean isCompleted = (Boolean) session.getAttribute("isCompleted");
                                    if (isCompleted != null && isCompleted) {
                                        double originalPrice = plan.getPrice();
                                        double discountPrice = originalPrice * 0.7;
                                %>
                                <p>Vì bạn đã từng hoàn thành khóa học.</p>
                                <span class="price mt-auto">
                                    <span style="text-decoration: line-through; color: gray; font-size: 69%;">
                                        <%= String.format("%,.0f", originalPrice)%> VND
                                    </span>
                                    &nbsp;
                                    <span style="color: red; font-weight: bold;">
                                        <%= String.format("%,.0f", discountPrice)%> VND
                                    </span>
                                </span>
                                <%
                                } else {
                                %>
                                <span class="price mt-auto"><%= String.format("%,.0f", plan.getPrice())%> VND</span>
                                <%
                                    }
                                %>

                            </div>
                            <div class="inner-button">
                                <form action="QuitPlanRegister" method="post">
                                    <input type="hidden" name="goal" value="<%= plan.getGoals()%>">
                                    <button type="submit" class="button">Đăng Ký</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>

        <!-- Support Chat Button -->
        <%
            String myrole = (String) session.getAttribute("role");
            String id = (String) session.getAttribute("id");
            if (myrole != null && id != null) {
        %>
        <% if ("member".equalsIgnoreCase(myrole)) {
                MemberDao mdao = new MemberDao();
                String coachId = mdao.getCoachIdByMemberId(id);
                if (coachId != null && !coachId.isEmpty()) {
        %>
        <form action="SupportServlet" method="get">
            <input type="hidden" name="idMember" value="<%= id%>">
            <button type="submit" class="support-button" title="Support with your Coach">
                <i class="fas fa-comments"></i>
            </button>
        </form>
        <% }
        } else {%>
        <form action="CoachSupportServlet" method="get">
            <input type="hidden" name="idCoach" value="<%= id%>">
            <button type="submit" class="support-button" title="Support with Members">
                <i class="fas fa-comments"></i>
            </button>
        </form>
        <% }
            }%>

        <%@include file="information/footer.jspf" %>

        <!--Login successful notification-->
        <div class="app-notification-box" id="box-login-notification">
            <div class="inner-logo">
                <i class="fa-solid fa-check"></i>
            </div>
            <div class="inner-content">
                <div class="inner-title">Thành Công</div>
                <div class="inner-subtitle">Bạn đã đăng nhập thành công</div>
            </div>
        </div>
        <!--End Login successful notification-->
        <script src="js/notification-sidebar.js"></script>

        <script>
                                    document.addEventListener('DOMContentLoaded', function () {
                                        const url = new URLSearchParams(window.location.search);
                                        if (url.has('login') && url.get('login') === 'success') {
                                            const toast = document.getElementById('box-login-notification');
                                            toast.classList.add('show');

                                            setTimeout(() => {
                                                toast.classList.remove('show');
                                            }, 5000);

                                            const urlNew = window.location.pathname + window.location.hash;
                                            history.replaceState(null, '', urlNew);
                                        }
                                    });
        </script>
    </body>
</html>
