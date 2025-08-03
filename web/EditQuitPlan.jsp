<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="DTO.QuitPlan" %>

<%-- Check for admin session --%>
<% if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    } %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Chỉnh sửa QuitPlan</title>
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
        <div class="main-content d-flex justify-content-center align-items-center" style="min-height: 100vh;">
            <div class="container">
                <% QuitPlan qp = (QuitPlan) request.getAttribute("qp");
                    if (qp == null) { %>
                <div class="alert alert-danger">Không tìm thấy kế hoạch để chỉnh sửa.</div>
                <% } else {%>
                <div class="card">
                    <div class="card-header bg-success text-white">
                        <h4 class="mb-0">Chỉnh sửa khóa học: <%= qp.getIdQuitPlan()%></h4>
                    </div>
                    <div class="card-body">
                        <form action="EditQuitPlanServlet" method="post">
                            <input type="hidden" name="idQuitPlan" value="<%= qp.getIdQuitPlan()%>"/>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="periodOfTime">Thời gian (tháng)</label>
                                    <input type="number" class="form-control" id="periodOfTime" name="periodOfTime" value="<%= qp.getPeriodOfTime()%>" required>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="price">Giá</label>
                                    <input type="number" step="0.01" class="form-control" id="price" name="price" value="<%= qp.getPrice()%>" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="goals">Mục tiêu (Goals)</label>
                                <input type="text" class="form-control" id="goals" name="goals" value="<%= qp.getGoals()%>" required>
                            </div>
                            <div class="form-group">
                                <label for="progress">Tiến độ (Progress)</label>
                                <input type="text" class="form-control" id="progress" name="progress" value="<%= qp.getProgress()%>" required>
                            </div>
                            <button type="submit" class="btn btn-success"><i class="fas fa-save"></i> Lưu thay đổi</button>
                            <a href="QuitplanManagerServlet" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Hủy</a>
                        </form>
                    </div>
                </div>
                <% }%>
            </div>
        </div>
        <!-- /Main Content -->
    </div>
</body>
</html>
