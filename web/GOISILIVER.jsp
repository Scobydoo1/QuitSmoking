<!DOCTYPE html>
<html lang="en">
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Chi Tiết Gói QuitPlan</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/QUITPLANstyle.css" />
        <link href="css/stylehomepage.css" rel="stylesheet" type="text/css"/>
        <%@include file="/information/bootstrap.jspf" %>
    </head>

    <body>
        <header>
            <!-- Header -->
            <%@include file="/information/header.jspf" %>

        </header>

        <main class="container">

            <div class="plan-content">
                <img src="<%=request.getContextPath()%>/images/home/plan-1.jpg" alt="Hình ảnh gói Copper" class="plan-image">

                <div class="plan-details">
                    <p class="plan-description">
                        Khóa học "Duy Trì và Ngăn Ngừa Tái Nghiện" là bước cuối cùng giúp người đã cai nghiện thuốc lá trên 3 tháng củng cố lối sống không thuốc lá và tránh nguy cơ tái nghiện dài hạn. Đây cũng là lựa chọn lý tưởng cho những ai muốn chia sẻ kinh nghiệm của mình, trở thành cố vấn hỗ trợ cộng đồng trong hành trình cai nghiện.
                    </p>
                    <h3>Chi tiết khóa học</h3>
                    <div class="course-info">
                        <div class="info-item">
                            <span class="label">Thời lượng : </span>
                            <span class="value">1 tháng (12 buổi)</span>
                        </div>
                        <div class="info-item">
                            <span class="label">Thời gian :</span>
                            <span class="value">2 giờ / buổi </span>
                        </div>
                        <div class="info-item">
                            <span class="label">Thời gian bắt đầu mỗi buổi học :</span>
                            <span class="value"> 07h00 / 09h15 / 13h00 / 15h15  </span>
                        </div>
                        <div class="info-item">
                            <span class="label">Hình thức học :</span>
                            <span class="value">Online qua Zoom </span>
                        </div>                   
                    </div>
                    <ul>
                        <li>
                            <h4>Đặc điểm của khóa học:</h4>
                            <ul>
                                <li>Hỗ trợ người tham gia xây dựng thói quen sống lành mạnh và tích cực, bất chấp các yếu tố kích thích từ môi trường xung quanh.</li>
                                <li>Cung cấp kiến thức và kỹ năng để duy trì lối sống không thuốc lá.</li>                           
                            </ul>
                        </li>
                        <li>
                            <h4>Hỗ trợ toàn diện:</h4>
                            <ul>
                                <li>Tổ chức các buổi học phát triển kỹ năng sống như quản lý căng thẳng, xây dựng kỷ luật bản thân và tăng cường mối quan hệ xã hội lành mạnh.</li>
                                <li>Cung cấp hướng dẫn cụ thể để trở thành một cố vấn, chia sẻ kinh nghiệm và truyền cảm hứng cho những người khác.</li>
                            </ul>
                        </li>
                        <li>
                            <h4>Ý nghĩa cộng đồng:</h4>
                            <ul>
                                <li>Giúp học viên không chỉ củng cố bản thân mà còn lan tỏa giá trị của việc cai nghiện, tạo động lực cho cộng đồng.</li>
                            </ul>
                        </li>
                    </ul>
                    <h3>Mục tiêu khóa học:</h3>
                    <ul>
                        <li>Đảm bảo người tham gia duy trì lối sống không thuốc lá trong dài hạn.</li>
                        <li>Trang bị khả năng đối phó với các yếu tố kích thích.</li>
                        <li>Xây dựng đội ngũ cố vấn từ những người từng trải để hỗ trợ cộng đồng cai nghiện hiệu quả hơn.</li>
                    </ul>
                    <form action="PaymentServlet" method="get">
                        <input type="hidden" name="goal" value="SILVER" /> <!-- Set giá trị goal -->
                        <input type="hidden" name="date" value="30" />
                        <%
                            Boolean isCompleted = (Boolean) session.getAttribute("isCompleted");
                            if (isCompleted != null && isCompleted) {
                                double originalPrice = 1000000;
                                double discountPrice = originalPrice * 0.7;
                        %>
                        <input type="hidden" name="price" value="700,000" />
                        <p class="plan-price">
                            Giá:
                            <span style="text-decoration: line-through; color: gray; font-size: 90%;">
                                <%= String.format("%,.0f", originalPrice)%> VND
                            </span>
                            &nbsp;
                            <span style="color: red; font-weight: bold;">
                                <%= String.format("%,.0f", discountPrice)%> VND
                            </span>
                        </p>
                        <%
                        } else {
                        %>
                        <p class="plan-price">Giá: 1,000,000 VND</p>
                        <input type="hidden" name="price" value="1,000,000" />
                        <%
                            }
                        %>
                        <button type="submit" class="btn-register">Đăng Ký Ngay</button>   </form>

                </div>
            </div>
        </main>

        <div class="footer">
            <div class="container">
                <div class="inner-footer" style="display: flex; justify-content: space-between; flex-wrap: wrap;">
                    <div class="footer-col">
                        <p class="inner-title">COMPANY NAME</p>
                        <p class="inner-desc">Fusce at libero iaculis, venenatis augue quis, pharetra lorem...</p>
                    </div>
                    <div class="footer-col">
                        <p class="inner-title">CONTACT</p>
                        <ul class="contact-list">
                            <li><i class="fas fa-phone"></i> 010-070-010</li>
                            <li><i class="fas fa-envelope"></i> info@gmail.com</li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <p class="inner-title">OPEN HOURS</p>
                        <ul class="hours-list">
                            <li>Monday - Friday 06:00 AM - 10:00 PM</li>
                            <li>Saturday 09:00 AM - 08:00 PM</li>
                            <li>Sunday Closed</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>