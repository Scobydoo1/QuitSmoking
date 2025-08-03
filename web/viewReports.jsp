<%-- 
    Document   : viewReports
    Created on : Jul 13, 2025, 2:09:05 PM
    Author     : Nguyen Tien Dat
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, DTO.ReportDTO" %>
<%
    List<ReportDTO> reportList = (List<ReportDTO>) request.getAttribute("reportList");
    int i = 0;
%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách báo cáo</title>
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
                    <h1 class="mb-2 text-black">Danh sách Đăng ký</h1>
                </div>

                <div class="table-responsive">
                    <table class="table table-hover table-bordered bg-white">
                        <thead class="bg-success text-white">
                            <tr>
                                <th>Số thứ tự</th>
                                <th>Tài khoản</th>
                                <th>Vai trò</th>
                                <th>Loại báo cáo</th>
                                <th>Link</th>
                                <th>Mô tả</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                if (reportList != null && !reportList.isEmpty()) {
                                    for (ReportDTO r : reportList) {
                                        i++;
                            %>
                            <tr>
                                <td><%= i%></td>
                                <td><%= r.getReporterID()%></td>
                                <td><%= r.getRole()%></td>
                                <td><%= r.getReportType()%></td>
                                <td>
                                    <% if (r.getLink() != null && !r.getLink().isEmpty()) {%>
                                    <a href="<%= r.getLink()%>" target="_blank"><%= r.getLink()%></a>
                                    <% } else { %>
                                    Không có
                                    <% }%>
                                </td>
                                <td><%= r.getDescription()%></td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr><td colspan="6" class="text-center">Không có báo cáo nào.</td></tr>
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
    </body>
</html>
