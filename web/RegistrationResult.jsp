<%@page import="DTO.RegistrationPayment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration List</title>
        <%@include file="information/bootstrap.jspf" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            .table-actions .btn {
                margin-right: 5px;
            }
            .table-bordered th,
            .table-bordered td {
                border: 1px solid #cccccc;
            }

            input:focus,
            select:focus,
            textarea:focus,
            .form-control:focus {
                outline: none;
                box-shadow: none;
                border-color: #ced4da; /* hoặc đổi thành màu bạn muốn */
            }

            body {
                background: #f0fff4; 
            }

        </style>
    </head>
    <body>
        <!-- /Sidebar -->

        <!-- Main Content -->
        <div class="main-content d-flex justify-content-center" style="padding: 40px 0;">
            <div class="container" style="max-width: 1100px;">
                <div class="title p-2 mb-2">
                    <h1 class="h2 mb-2 text-black">Danh sách Đăng ký</h1>
                </div>
                <%
                    // Lấy danh sách các bản ghi từ request
                    List<RegistrationPayment> registrations = (List<RegistrationPayment>) request.getAttribute("registrations");
                    String error = (String) request.getAttribute("error");
                    // Nếu có lỗi, hiển thị thông báo lỗi
                    if (error != null) {
                %>
                <div class="alert alert-danger" role="alert">
                    <strong>Lỗi: </strong><%= error%>
                </div>
                <%
                    }
                %>

                <div class="table-responsive">
                    <table class="table table-hover table-bordered bg-white">
                        <thead class="bg-success text-white">
                            <tr>
                                <th>ID Đăng ký</th>
                                <th>ID Thành viên</th>
                                <th>ID Thanh toán</th>
                                <th>ID Kế hoạch bỏ thuốc</th>
                                <th>Trạng thái</th>
                                <th>Ngày đăng ký</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                if (registrations != null && !registrations.isEmpty()) {
                                    // Duyệt qua danh sách và hiển thị thông tin từng bản ghi
                                    for (RegistrationPayment reg : registrations) {
                            %>
                            <tr>
                                <td><%= reg.getIDRegistration()%></td>  <!-- Hiển thị IDRegistration -->
                                <td><%= reg.getIDMember()%></td>  <!-- Hiển thị IDMember -->
                                <td><%= reg.getIDPayment()%></td>  <!-- Hiển thị IDPayment -->
                                <td><%= reg.getIDQuitPlan()%></td>  <!-- Hiển thị IDQuitPlan -->
                                <td><%= reg.getStatus()%></td>  <!-- Hiển thị Status -->
                                <td><%= reg.getRegisterDate()%></td>  <!-- Hiển thị RegisterDate -->
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="6" class="text-center">Không có đăng ký nào.</td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                <a href="adminDashboard.jsp" class="btn btn-secondary mt-3">
                    <i class="fas fa-arrow-left"></i> Trở về trang chủ
                </a>
            </div>
        </div>
        <!-- /Main Content -->
    </div>
</body>
</html>