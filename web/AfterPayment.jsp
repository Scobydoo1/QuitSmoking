<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán Thành Công - Health Center</title>
    <link rel="stylesheet" href="css/payment-success.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="success-container">
        <!-- Success Animation -->
        <div class="success-animation">
            <div class="checkmark-circle">
                <div class="checkmark">
                    <i class="fas fa-check"></i>
                </div>
            </div>
        </div>

        <!-- Success Content -->
        <div class="success-content">
            <h1 class="success-title">
                <i class="fas fa-party-horn"></i>
                Thanh toán thành công!
            </h1>
            
            <p class="success-message">
                Cảm ơn bạn đã thực hiện thanh toán. Chúng tôi đã nhận được giao dịch của bạn.
            </p>

            <!-- Payment Info Card -->
            <div class="payment-info-card">
                <div class="card-header">
                    <i class="fas fa-credit-card"></i>
                    <h3>Thông tin thanh toán</h3>
                </div>
                <div class="card-body">
                    <div class="info-row">
                        <span class="info-label">Trạng thái:</span>
                        <span class="info-value success-status">
                            <i class="fas fa-check-circle"></i>
                            Thành công
                        </span>
                    </div>
                    <div class="info-row">
                        <span class="info-label">Thời gian:</span>
                        <span class="info-value">
                            <%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()) %>
                        </span>
                    </div>
                </div>
            </div>

            <!-- Coach Info Card - Đã xóa ID Coach -->
            <div class="coach-info-card">
                <div class="card-header">
                    <i class="fas fa-user-tie"></i>
                    <h3>Thông tin Huấn Luyện Viên</h3>
                </div>
                <div class="card-body">
                    <div class="coach-profile">
                        <div class="coach-avatar">
                            <i class="fas fa-user-circle"></i>
                        </div>
                        <div class="coach-details">
                            <!-- Chỉ hiển thị tên huấn luyện viên, đã xóa ID Coach -->
                            <div class="info-row">
                                <span class="info-label">Huấn luyện viên của bạn:</span>
                                <span class="info-value coach-name">
                                    <%= request.getAttribute("coachName") != null ? request.getAttribute("coachName") : "Chưa được phân công" %>
                                </span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="coach-contact">
                        <p class="contact-note">
                            <i class="fas fa-info-circle"></i>
                            Huấn luyện viên sẽ liên hệ với bạn trong vòng 24 giờ tới để bắt đầu chương trình.
                        </p>
                    </div>
                </div>
            </div>

            <!-- Next Steps -->
            <div class="next-steps-card">
                <div class="card-header">
                    <i class="fas fa-route"></i>
                    <h3>Bước tiếp theo</h3>
                </div>
                <div class="card-body">
                    <div class="steps-list">
                        <div class="step-item">
                            <div class="step-number">1</div>
                            <div class="step-content">
                                <h4>Chờ liên hệ</h4>
                                <p>Huấn luyện viên sẽ liên hệ với bạn để lên lịch buổi tư vấn đầu tiên</p>
                            </div>
                        </div>
                        <div class="step-item">
                            <div class="step-number">2</div>
                            <div class="step-content">
                                <h4>Bắt đầu chương trình</h4>
                                <p>Tham gia các hoạt động cai thuốc theo hướng dẫn của huấn luyện viên</p>
                            </div>
                        </div>
                        <div class="step-item">
                            <div class="step-number">3</div>
                            <div class="step-content">
                                <h4>Theo dõi tiến độ</h4>
                                <p>Cập nhật tiến trình hàng ngày và nhận hỗ trợ từ đội ngũ chuyên gia</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Action Buttons -->
            <div class="action-buttons">
                <a href="homepage.jsp" class="btn btn-primary">
                    <i class="fas fa-home"></i>
                    Quay lại trang chủ
                </a>
                <a href="DetailMemberProfile" class="btn btn-secondary">
                    <i class="fas fa-user"></i>
                    Xem hồ sơ
                </a>
                <a href="progress.jsp" class="btn btn-outline">
                    <i class="fas fa-chart-line"></i>
                    Bắt đầu ghi nhật ký
                </a>
            </div>
        </div>

        <!-- Confetti Animation -->
        <div class="confetti">
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
        </div>
    </div>

    <script src="js/payment-success.js"></script>
</body>
</html>
