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
            <%@include file="/information/header.jspf" %>

        </header>

        <main class="container">

            <div class="plan-content">
                <img src="<%=request.getContextPath()%>/images/home/plan-1.jpg" alt="Hình ảnh gói Copper" class="plan-image">

                <div class="plan-details">
                    <p class="plan-description">
                        Khóa học trung cấp phù hợp cho những người đang trong quá trình chuyển đổi hành vi, hướng tới thay thế thuốc lá bằng các thói quen lành mạnh và bền vững.
                    </p>
                    <h3>Chi tiết khóa học</h3>
                    <div class="course-info">
                        <div class="info-item">
                            <span class="label">Thời lượng : </span>
                            <span class="value">3 tháng (36 buổi)</span>
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
                                <li>Giúp học viên xây dựng và duy trì các thói quen mới thay thế hành vi hút thuốc.</li>
                                <li>Tập trung vào kỹ năng quản lý căng thẳng và tránh các yếu tố gây tái nghiện.</li>                           
                            </ul>
                        </li>
                        <li>
                            <h4>Hỗ trợ toàn diện:</h4>
                            <ul>
                                <li>Hướng dẫn kỹ thuật thư giãn, kiểm soát cảm xúc và phản ứng trước áp lực.</li>
                                <li>Hỗ trợ lập kế hoạch hoạt động hằng ngày nhằm duy trì động lực sống khỏe.</li>
                                <li>Cung cấp tài nguyên đa phương tiện và tư vấn định kỳ từ chuyên gia.</li>
                            </ul>
                        </li>
                        <li>
                            <h4>Ý nghĩa cộng đồng:</h4>
                            <ul>
                                <li>Khuyến khích người tham gia kết nối với những người cùng mục tiêu để tăng cường động lực.</li>
                                <li>Xây dựng mối quan hệ xã hội tích cực hỗ trợ nhau trong hành trình cai nghiện.</li>
                            </ul>
                        </li>
                    </ul>

                    <h3>Mục tiêu khóa học:</h3>
                    <ul>
                        <li>Giúp người học củng cố thói quen mới, ngăn chặn sự quay trở lại với thuốc lá.</li>
                        <li>Định hình một lối sống lành mạnh về thể chất lẫn tinh thần.</li>



                    </ul>
                    <form action="PaymentServlet" method="get">
                        <input type="hidden" name="goal" value="GOLD" /> <!-- Set giá trị goal -->
                          <input type="hidden" name="date" value="90" />

                        <%
                            Boolean isCompleted = (Boolean) session.getAttribute("isCompleted");
                            if (isCompleted != null && isCompleted) {
                                double originalPrice = 2500000;
                                double discountPrice = originalPrice * 0.7;
                        %>
                        <input type="hidden" name="price" value="1,750,000" />
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
                        <p class="plan-price">Giá: 2,500,000 VND</p>
                        <input type="hidden" name="price" value="2,500,000" />
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