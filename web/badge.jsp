<%-- 
    Document   : badge
    Created on : Jun 22, 2025, 5:24:32 PM
    Author     : Nguyen Tien Dat
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DTO.Badge" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Huy hiệu của bạn - Quit Smoking App</title>
    <link rel="stylesheet" href="css/badge.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <!-- HEADER SECTION - Phần đầu trang -->
    <div class="header-container">
        <div class="header-content">
            <div class="header-icon">
                <i class="fas fa-medal"></i>
            </div>
            <div class="header-text">
                <h1>Huy hiệu của bạn</h1>
                <p>Những thành tựu bạn đã đạt được trong hành trình cai thuốc</p>
            </div>
        </div>
    </div>

    <!-- MAIN CONTENT CONTAINER - Container chính chứa nội dung -->
    <div class="main-container">
        
        <!-- ERROR MESSAGE SECTION - Phần hiển thị thông báo lỗi -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <div class="error-message-container">
            <div class="error-message">
                <i class="fas fa-exclamation-triangle"></i>
                <span><%= errorMessage %></span>
            </div>
        </div>
        <%
            }
        %>

        <!-- BADGES SECTION - Phần hiển thị danh sách huy hiệu -->
        <div class="badges-section">
            <%
                List<Badge> badgeList = (List<Badge>) request.getAttribute("badgeList");
                if (badgeList == null || badgeList.isEmpty()) {
            %>
                <!-- EMPTY STATE - Trạng thái khi chưa có huy hiệu -->
                <div class="empty-state-container">
                    <div class="empty-state">
                        <div class="empty-icon">
                            <i class="fas fa-trophy"></i>
                        </div>
                        <h3>Chưa có huy hiệu nào</h3>
                        <p>Hãy tiếp tục cai thuốc để nhận được huy hiệu đầu tiên của bạn!</p>
                        <div class="motivation-text">
                            <p><i class="fas fa-star"></i> Mỗi ngày không hút thuốc là một chiến thắng</p>
                        </div>
                    </div>
                </div>
            <%
                } else {
            %>
                <!-- BADGE STATS - Thống kê huy hiệu -->
                <div class="badge-stats-container">
                    <div class="badge-stats">
                        <div class="stat-item">
                            <div class="stat-number"><%= badgeList.size() %></div>
                            <div class="stat-label">Huy hiệu đã đạt</div>
                        </div>
                        <div class="stat-divider"></div>
                        <div class="stat-item">
                            <div class="stat-number">
                                <i class="fas fa-fire"></i>
                            </div>
                            <div class="stat-label"></div>
                        </div>
                    </div>
                </div>

                <!-- BADGES GRID - Lưới hiển thị huy hiệu -->
                <div class="badges-grid-container">
                    <div class="badges-grid">
                        <%
                            for (int i = 0; i < badgeList.size(); i++) {
                                Badge b = badgeList.get(i);
                        %>
                            <!-- INDIVIDUAL BADGE CARD - Thẻ huy hiệu đơn lẻ -->
                            <div class="badge-card" data-badge-index="<%= i %>">
                                <!-- Badge Header - Phần đầu thẻ huy hiệu -->
                                <div class="badge-header">
                                    <div class="badge-icon">
                                        <i class="fas fa-award"></i>
                                    </div>
                                    <div class="badge-rank">
                                        #<%= i + 1 %>
                                    </div>
                                </div>
                                
                                <!-- Badge Content - Nội dung chính của huy hiệu -->
                                <div class="badge-content">
                                    <h3 class="badge-name"><%= b.getName() %></h3>
                                    
                                    <!-- Badge Condition - Điều kiện nhận huy hiệu -->
                                    <div class="badge-condition">
                                        <div class="condition-label">
                                            <i class="fas fa-target"></i>
                                            <span>Điều kiện</span>
                                        </div>
                                        <p><%= b.getCondition() %></p>
                                    </div>
                                    
                                    <!-- Badge Description - Mô tả huy hiệu -->
                                    <div class="badge-description">
                                        <div class="description-label">
                                            <i class="fas fa-info-circle"></i>
                                            <span>Mô tả</span>
                                        </div>
                                        <p><%= b.getDescription() %></p>
                                    </div>
                                </div>
                                
                                <!-- Badge Footer - Phần cuối thẻ huy hiệu -->
                                <div class="badge-footer">
                                    <div class="achievement-date">
                                        <i class="fas fa-calendar-check"></i>
                                        <span>Đã đạt được</span>
                                    </div>
                                </div>
                            </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            <%
                }
            %>
        </div>

        <!-- NAVIGATION SECTION - Phần điều hướng -->
        <div class="navigation-container">
            <div class="navigation-buttons">
                <a href="homepage.jsp" class="back-button">
                    <i class="fas fa-arrow-left"></i>
                    <span>Quay lại trang chủ</span>
                </a>
                
                <a href="progressList.jsp" class="progress-button">
                    <i class="fas fa-chart-line"></i>
                    <span>Xem tiến trình</span>
                </a>
            </div>
        </div>
    </div>

    <!-- FLOATING ACTION BUTTON - Nút hành động nổi -->
    <div class="fab-container">
        <button class="fab" onclick="scrollToTop()">
            <i class="fas fa-chevron-up"></i>
        </button>
    </div>

    <script>
        // Scroll to top function
        function scrollToTop() {
            window.scrollTo({
                top: 0,
                behavior: 'smooth'
            });
        }

        // Show FAB when scrolling
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

        // Add animation to badge cards
        document.addEventListener('DOMContentLoaded', function() {
            const badgeCards = document.querySelectorAll('.badge-card');
            badgeCards.forEach((card, index) => {
                card.style.animationDelay = (index * 0.1) + 's';
            });
        });
    </script>
</body>
</html>

