<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DTO.Coach" %>

<%-- Check for admin session --%>
<% if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    } %>

<% Coach c = (Coach) request.getAttribute("coach"); %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Sửa thông tin Coach</title>
        <%@include file="information/bootstrap.jspf" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            input:focus,
            select:focus,
            textarea:focus,
            .form-control:focus {
                outline: none;
                box-shadow: none;
                border-color: #ced4da;
            }
            
        </style>

    </head>
    <body style="background: #f0fff4">
        <!-- Main Content -->
        <div class="main-content d-flex justify-content-center align-items-center" style="min-height: 100vh;">
            <div class="container">
                <div class="card">
                    <div class="card-header bg-success text-white">
                        <h4 class="mb-0">Chỉnh sửa thông tin Huấn luyện viên</h4>
                    </div>
                    <div class="card-body">
                        <% if (c != null) {%>
                        <form action="EditCoachServlet" method="post">
                            <input type="hidden" name="IDCoach" value="<%= c.getIDCoach()%>"/>
                            <div class="form-group mb-3">
                                <label for="coachName">Họ tên</label>
                                <input type="text" class="form-control" id="coachName" name="coachName" value="<%= c.getCoachName()%>">
                            </div>
                            <div class="form-group mb-3">
                                <label>Giới tính</label>
                                <div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender" id="male" value="male" <%= "male".equalsIgnoreCase(c.getGender()) ? "checked" : ""%>>
                                        <label class="form-check-label" for="male">Nam</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender" id="female" value="female" <%= "female".equalsIgnoreCase(c.getGender()) ? "checked" : ""%>>
                                        <label class="form-check-label" for="female">Nữ</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row mb-3">
                                <div class="form-group col-md-6">
                                    <label for="phone">Điện thoại</label>
                                    <input type="text" class="form-control" id="phone" name="phone" value="<%= c.getPhone()%>">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" value="<%= c.getEmail()%>">
                                </div>
                            </div>
                            <div class="form-group mb-3">
                                <label for="address">Địa chỉ</label>
                                <input type="text" class="form-control" id="address" name="address" value="<%= c.getAddress()%>">
                            </div>
                            <div class="form-row mb-3">
                                <div class="form-group col-md-6">
                                    <label for="specialization">Chuyên môn</label>
                                    <input type="text" class="form-control" id="specialization" name="specialization" value="<%= c.getSpecialization()%>">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="experienceYears">Kinh nghiệm (năm)</label>
                                    <input type="number" class="form-control" id="experienceYears" name="experienceYears" value="<%= c.getExperienceYears()%>">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-success">
                                <i class="fas fa-save"></i> Lưu thay đổi
                            </button>
                            <a href="ManageCoachServlet" class="btn btn-secondary">
                                <i class="fas fa-times-circle"></i> Hủy
                            </a>
                        </form>
                        <% } else { %>
                        <div class="alert alert-danger">Không tìm thấy thông tin coach.</div>
                        <% }%>
                    </div>
                </div>
            </div>
            <!-- /Main Content -->
        </div>
    </body>
</html>
