<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="DTO.QuitPlan"%>

<%-- Check for admin session --%>
<% if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    } %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Quản lý Quitplan</title>
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
        <!-- Main Content -->
        <div class="main-content d-flex justify-content-center" style="padding: 40px 0;">
            <div class="container" style="max-width: 1100px;">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <div class="title p-2 mb-2">
                        <h1 class="mb-2 text-center text-black">Danh sách Quitplan</h1>
                    </div>
                    <!-- Bạn có thể thêm nút "Thêm mới" ở đây nếu cần -->
                </div>

                <div class="table-responsive">
                    <table class="table table-striped table-hover table-bordered bg-white">
                        <thead class="bg-success text-white text-center">
                            <tr>
                                <th>ID</th>
                                <th>Thời gian (tháng)</th>
                                <th>Mục tiêu</th>
                                <th>Tiến độ</th>
                                <th>Giá</th>
                                <th class="text-center"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% List<QuitPlan> list = (List<QuitPlan>) request.getAttribute("quitplans");
                                if (list != null && !list.isEmpty()) {
                                    for (QuitPlan qp : list) {%>
                            <tr class="align-middle">
                                <td class="text-center"><%= qp.getIdQuitPlan()%></td>
                                <td class="text-center"><%= qp.getPeriodOfTime()%></td>
                                <td><%= qp.getGoals()%></td>
                                <td><%= qp.getProgress()%></td>
                                <td class="text-right"><%= String.format("%,.0f", qp.getPrice())%> VND</td>
                                <td class="text-center">
                                    <a href="EditQuitPlanServlet?idQuitPlan=<%= qp.getIdQuitPlan()%>" class="btn btn-sm btn-info" title="Sửa">
                                        <i class="fas fa-edit"></i> Sửa
                                    </a>
                                </td>
                            </tr>
                            <% }
                            } else { %>
                            <tr>
                                <td colspan="6" class="text-center text-muted">Không có kế hoạch nào.</td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>

                <a href="adminDashboard.jsp" class="btn btn btn-secondary mt-3">
                    <i class="fas fa-arrow-left"></i> Trở về trang chủ
                </a>
            </div>
        </div>

        <!-- /Main Content -->
    </body>
</html>
