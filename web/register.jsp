<!doctype html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

        <link rel="stylesheet" href="css/styleregister.css">
        <link rel="stylesheet" href="css/stylehomepage.css">
        <title>Create Account Page</title>      
    </head>

    <body>
        
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
        
        <style>
            /* Home Button */
            .home-button {
                position: fixed; /* Cố định vị trí */
                top: 20px; /* Cách mép trên 20px */
                left: 20px; /* Cách mép trái 20px */
                background-color: #28a745; /* Màu xanh lá chủ đạo */
                color: white; /* Màu chữ */
                padding: 10px 20px; /* Khoảng cách bên trong nút */
                font-size: 16px; /* Kích thước chữ */
                font-weight: bold; /* Chữ đậm */
                border: none; /* Không viền */
                border-radius: 8px; /* Bo góc */
                text-decoration: none; /* Loại bỏ gạch chân */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
                transition: all 0.3s ease; /* Hiệu ứng chuyển động */
                z-index: 999; /* Hiển thị trên cùng */
            }

            .home-button:hover {
                background-color: #218838; /* Màu đậm hơn khi hover */
                box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2); /* Đổ bóng tăng khi hover */
                transform: scale(1.05); /* Hiệu ứng phóng to nhẹ */
                text-decoration: none;
            }

        </style>
        <a href="homepage.jsp" class="home-button">Trang chủ</a>

        
        <div class="register-form">
            <div class="container">
                <div class="inner-form">
                    <c:if test="${not empty errorMessage}">
                    <div class="error-message">
                        <p>${errorMessage}</p>
                    </div>
                </c:if>

                <c:if test="${not empty successMessage}">
                    <div class="success-message">
                        <p>${successMessage}</p>
                    </div>
                </c:if>
                    <h2 class="inner-title">Đăng Ký</h2>
                    <form action="RegisterServlet" method="post">
                    <div class="form-group">
                        <label for="memberid">ID Member</label>
                        <input type="text" class="form-control" name="memberid"
                               value="${memberId}" required>
                    </div>
                    <div class="form-group">
                        <label for="fullname">Họ và tên</label>
                        <input type="text" class="form-control" name="fullname"
                               value="${fullname}" required>
                    </div>
                    <div class="form-group">
                        <label>Giới tính</label>
                        <div class="inner-form-gender">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" value="male"
                                       ${gender=='male' ? 'checked' : ''}>
                                <label class="form-check-label">Nam</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" value="female"
                                       ${gender=='female' ? 'checked' : ''}>
                                <label class="form-check-label">Nữ</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" value="other"
                                       ${gender=='other' ? 'checked' : ''}>
                                <label class="form-check-label">Khác</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" name="email"
                               value="${email}" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Mật khẩu</label>
                        <input type="password" class="form-control" name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Xác nhận mật khẩu</label>
                        <input type="password" class="form-control" name="confirmPassword" required>
                    </div>
                    <div class="form-group">
                        <label for="phone">Số điện thoại</label>
                        <input type="tel" class="form-control" name="phone"
                               value="${phone}" required>
                    </div>
                    <div class="form-group">
                        <label for="address">Địa chỉ</label>
                        <textarea name="address" class="form-control" rows="2" required>${address}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="dob">Ngày sinh</label>
                        <input type="date" class="form-control" name="dob"
                               value="${dob}" required>
                    </div>
                    <button type="submit" class="btn btn-green btn-block">Đăng ký</button>
                </form>
                </div>
                
            </div>
        </div>

    </body>

</html>