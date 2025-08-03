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
                        Khóa học nâng cao dành cho người mới bắt đầu và muốn cắt đứt hoàn toàn sự phụ thuộc vào thuốc lá. Đây là bước ngoặt để hình thành tư duy tích cực và lối sống sạch thuốc một cách bền vững.
                    </p>
                    <h3>Chi tiết khóa học</h3>
                    <div class="course-info">
                        <div class="info-item">
                            <span class="label">Thời lượng : </span>
                            <span class="value">6 tháng (72 buổi)</span>
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
                                <li>Giúp người tham gia tái cấu trúc hành vi, loại bỏ hoàn toàn cảm giác lệ thuộc vào nicotine.</li>
                                <li>Củng cố tư duy tích cực, tự tin duy trì cuộc sống không thuốc lá trong mọi tình huống.</li> 
                                <li>Định hướng hình thành một môi trường sống lành mạnh, hỗ trợ lâu dài.</li>
                            </ul>
                        </li>
                        <li>
                            <h4>Hỗ trợ toàn diện:</h4>
                            <ul>
                                <li>Cung cấp các bài học chuyên sâu về thay đổi hành vi, duy trì động lực và kiểm soát các thói quen xấu.</li>
                                <li>Hướng dẫn xây dựng mạng lưới hỗ trợ từ bạn bè, gia đình và cộng đồng.</li>
                                <li>Tổ chức các buổi huấn luyện để củng cố kỹ năng duy trì thói quen mới.</li>
                            </ul>
                        </li>
                        <li>
                            <h4>Ý nghĩa cộng đồng:</h4>
                            <ul>
                                <li>Tạo động lực cho người tham gia phát triển hơn trong cuộc sống tái hòa nhập và chung tay phát triển xã hội</li>
                                <li>Đóng góp vào việc xây dựng cộng đồng sống khỏe mạnh, không khói thuốc.</li>
                            </ul>
                        </li>
                    </ul>
                    <h3>Mục tiêu khóa học:</h3>
                    <ul>
                        <li>Đảm bảo người tham gia không tái nghiện dù trong môi trường có yếu tố kích thích cao.</li>
                        <li>Trang bị nền tảng tâm lý và xã hội vững chắc để duy trì thói quen tốt suốt đời.</li>
                    </ul>

                    <form action="PaymentServlet" method="get">
                        <input type="hidden" name="goal" value="DIAMOND" /> <!-- Set giá trị goal -->
                          <input type="hidden" name="date" value="120" />

                        
                        <%
                            Boolean isCompleted = (Boolean) session.getAttribute("isCompleted");
                            if (isCompleted != null && isCompleted) {
                                double originalPrice = 4500000;
                                double discountPrice = originalPrice * 0.7;
                        %>
                        <input type="hidden" name="price" value="3,150,000" />
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
                        <p class="plan-price">Giá: 4,500,000 VND</p>
                        <input type="hidden" name="price" value="4,500,000" />
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