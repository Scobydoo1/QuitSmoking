
<%@page import="DTO.Coach"%>
<%@page import="DTO.Member"%>
<!DOCTYPE html>
<html lang="vi">
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hồ sơ </title>
        <%
            Member member = (Member) request.getAttribute("member");
            Coach coach = (Coach) request.getAttribute("coach");
        %>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="css/user-profile.css">

    </head>

    <body>
        <style>
            /* Home Button */
            .home-button {
                position: fixed; /* C? ??nh v? trí */
                top: 20px; /* Cách mép trên 20px */
                left: 20px; /* Cách mép trái 20px */
                background-color: #28a745; /* Màu xanh lá ch? ??o */
                color: white; /* Màu ch? */
                padding: 10px 20px; /* Kho?ng cách bên trong nút */
                font-size: 16px; /* Kích th??c ch? */
                font-weight: bold; /* Ch? ??m */
                border: none; /* Không vi?n */
                border-radius: 8px; /* Bo góc */
                text-decoration: none; /* Lo?i b? g?ch chân */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* ?? bóng nh? */
                transition: all 0.3s ease; /* Hi?u ?ng chuy?n ??ng */
                z-index: 999; /* Hi?n th? trên cùng */
            }

            .home-button:hover {
                background-color: #218838; /* Màu ??m h?n khi hover */
                box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2); /* ?? bóng t?ng khi hover */
                transform: scale(1.05); /* Hi?u ?ng phóng to nh? */
                text-decoration: none;
            }

        </style>
        <a href="homepage.jsp" class="home-button">Home</a>
        <div class="user-profile">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <div class="card profile-card">
                            <div class="profile-header">
                            </div>
                            <div class="text-center profile-image-container">
                                <img src="<%= member.getImage() != null ? member.getImage() : "images/avata/nullavata.png"%>" alt="Ảnh đại diện" class="profile-image" />

                            </div>
                            <div class="card-body profile-body">
                                <h3 class="card-title text-center mb-4">Thông tin Hồ sơ</h3>

                                <div class="detail-row">
                                    <span class="detail-label">Tên đăng nhập:</span>
                                    <span class="detail-value" ><%= member.getIDMember()%></span>
                                </div>
                                <div class="detail-row">
                                    <span class="detail-label">Mật khẩu:</span>
                                    <span class="detail-value">********</span>
                                </div>
                                <div class="detail-row">
                                    <span class="detail-label">Họ và tên:</span>
                                    <span class="detail-value"><%= member.getMemberName()%></span>
                                </div>
                                <div class="detail-row">
                                    <span class="detail-label">Giới Tính</span>
                                    <span class="detail-value"><%= member.getGender()%></span>
                                </div>
                                <div class="detail-row">
                                    <span class="detail-label">Your Coach</span>
                                    <span class="detail-value"><%= (coach != null ? coach.getCoachName() : "Chưa có")%></span>
                                </div>
                                <div class="detail-row">
                                    <span class="detail-label">Ngày sinh:</span>
                                    <span class="detail-value"><%= member.getDateOfBirth()%></span>
                                </div>
                                <div class="detail-row">
                                    <span class="detail-label">Ngày tham gia:</span>
                                    <span class="detail-value"><%= member.getJoinDate()%></span>
                                </div>
                                <div class="detail-row">
                                    <span class="detail-label">Điện thoại:</span>
                                    <span class="detail-value"><%= member.getPhone()%></span>
                                </div>
                                <div class="detail-row">
                                    <span class="detail-label">Địa chỉ:</span>
                                    <span class="detail-value"><%= member.getAddress()%></span>
                                </div>

                                <div class="detail-row">
                                    <span class="detail-label">Email:</span>
                                    <span class="detail-value"><%= member.getEmail()%></span>
                                </div>

                                <div class="detail-row">
                                    <span class="detail-label">Trạng thái:</span>
                                    <span class="detail-value">
                                        <%= member.getStatus()%>
                                    </span>
                                </div>

                                <div class="detail-row">
                                    <span class="detail-label">Mô Tả</span>
                                    <span class="detail-value"><%= member.getSubscription()%></span>
                                </div>

                                <div class="text-center mt-4">
                                    <a href="UpdateProfileServlet?idMember=<%= member.getIDMember()%>" class="btn btn-success">
                                        Chỉnh sửa Hồ sơ
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2+UjszW/vFfrFWK7xM1uW+R5c+z4fN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
    </body>


</html>