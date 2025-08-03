<%-- 
    Document   : Payment
    Created on : Jun 15, 2025, 3:38:10 PM
    Author     : thanh
--%>

<%@page import="DTO.Payment"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="DTO.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Xác nhận thanh toán - Vietnamese Payment</title>
        <%
            String goal = (String) request.getAttribute("goal");
            String price = (String) request.getAttribute("price");
            String date = (String) request.getAttribute("date");
            Member member = (Member) request.getAttribute("member");
            List<Payment> payments = (List<Payment>) request.getAttribute("payments");

        %>
        <link rel="stylesheet" href="css/PaymentStyle.css">
    </head>
    <body>
        <!-- Header -->
        <div class="header">
            <div class="container">
                <div class="header-content">
                    <h1>Xác nhận thanh toán</h1>
                    <p>Kiểm tra thông tin và chọn phương thức thanh toán</p>
                </div>
            </div>
        </div>

        <div class="container">

            <div class="form-grid">
                <!-- Left Column -->
                <div class="left-column">
                    <!-- Customer Information -->
                    <div class="card">
                        <div class="card-header">
                            <div class="icon user-icon"></div>
                            <h2>Thông tin khách hàng</h2>
                        </div>

                        <div class="verification-badge">
                            <div class="check-icon"></div>
                            <div>
                                <span class="badge-title">Thông tin đã được xác thực</span>
                                <p class="badge-subtitle">Dữ liệu được tự động ký từ cơ sở dữ liệu hệ thống</p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="customerName">Họ và tên</label>
                            <input type="text" id="customerName" name="customerName" value="<%= member.getMemberName()%>" required>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <div class="input-with-icon">
                                    <div class="icon mail-icon"></div>
                                    <input type="email" id="email" name="email" value="<%= member.getEmail()%>" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="phone">Số điện thoại</label>
                                <div class="input-with-icon">
                                    <div class="icon phone-icon"></div>
                                    <input type="tel" id="phone" name="phone" value="<%= member.getPhone()%>" required>
                                </div>
                            </div>
                        </div>

                        <!--                        <div class="form-group">
                                                    <label for="idNumber">ID</label>
                                                    <div class="input-with-icon">
                                                        <div class="icon hash-icon"></div>
                                                        <input type="text" id="idNumber" name="idNumber" value="123456789012" required>
                                                    </div>
                                                </div>
                        -->
                        <div class="verification-success">
                            <div class="check-icon"></div>
                            <span>Thông tin đã được xác thực từ cơ sở dữ liệu</span>
                        </div>
                    </div>

                    <!-- Payment Methods -->

                    <div class="card">
                        <div class="card-header">
                            <div class="icon credit-card-icon"></div>
                            <h2>Phương thức thanh toán</h2>
                        </div>
                        <%
                            for (Payment payment : payments) {
                        %>

                        <div class="payment-methods">

                            <div class="payment-method" data-method="">
                                <input type="radio" id="${payment.getIdPayment()}" name="paymentMethod" value="${payment.getIdPayment()}" required>

                                <label for="momo">
                                    <div class="method-content">
                                        <div class="method-icon ">   <img src="<%= payment.getLogo()%>" style="width: 100%"alt="<%= payment.getMethod()%> Logo">  </div>
                                        <div class="method-info">
                                            <h3><%= payment.getMethod()%></h3>

                                        </div>
                                    </div>
                                    <div class="chevron-icon"></div>
                                </label>
                            </div>

                        </div>

                        <%}%>
                    </div>
                </div>

                <!-- Right Column - Order Summary -->
                <div class="right-column">
                    <div class="card">
                        <div class="card-header">
                            <div class="icon package-icon"></div>
                            <h2>Thông tin đơn hàng</h2>
                        </div>


                        <div class="package-card">
                            <div class="package-header">
                                <div class="package-name">Gói: <%= goal%></div>
                                <div class="package-price">Giá: <%= price%> đ</div>
                            </div>
                            <div class="package-duration">Thời hạn: <%= date%> ngày</div>

                            <div class="package-features">
                                <h4>Tính năng bao gồm:</h4>
                                <ul class="feature-list">
                                    <li>Truy cập không giới hạn</li>
                                    <li>Hỗ trợ 24/7</li>
                                    <li>Tính năng cao cấp</li>
                                    <li>Không quảng cáo</li>
                                </ul>
                            </div>
                        </div>


                        <div class="pricing-summary">
                            <div class="price-row">
                                <span>Giá gói dịch vụ:</span>
                                <span> <%= price%></span>
                            </div>

                            <div class="price-row total">
                                <span>Tổng cộng:</span>
                                <span> <%= price%>đ</span>
                            </div>
                        </div>
                        <form action="PaymentServlet" method="post">
                            <input type="hidden" name="goal" value=" <%= goal%>">
                            <!-- Ví dụ phương thức thanh toán -->

                            <button type="submit" class="payment-button">
                                Xác nhận thanh toán
                            </button>

<!--                            <div class="security-note">
                                Giao dịch được bảo mật SSL 256-bit
                            </div>-->
                        </form>
                    </div>

                </div>
            </div>


        </div>


    </body>
</html>