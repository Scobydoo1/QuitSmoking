<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, DTO.Coach" %>

<%-- Check for admin session --%>
<% if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    } %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Quản lý Huấn luyện viên</title>
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
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2 class="h2">Danh sách Huấn luyện viên</h2>
                    <a href="adminAddCoach.jsp" class="btn btn-success">
                        <i class="fas fa-plus-circle"></i> Tạo Coach mới
                    </a>
                </div>

                <div class="table-responsive">
                    <table class="table table-hover table-bordered bg-white">
                        <thead class="bg-success text-white">
                            <tr>
                                <th scope="col" class="text-center align-middle">ID</th>
                                <th scope="col" class="text-center align-middle">Tên</th>
                                <th scope="col" class="text-center align-middle">Giới tính</th>
                                <th scope="col" class="text-center align-middle">Điện thoại</th>
                                <th scope="col" class="text-center align-middle">Email</th>
                                <th scope="col" class="text-center align-middle">Chuyên môn</th>
                                <th scope="col" class="text-center align-middle">Kinh nghiệm</th>
                                <th scope="col" class="text-center align-middle">Trạng thái</th>
                                <th class="text-center al"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% ArrayList<Coach> coachList = (ArrayList<Coach>) request.getAttribute("coachList");
                                if (coachList != null && !coachList.isEmpty()) {
                                    for (Coach c : coachList) {%>
                            <tr>
                                <th scope="row" class="text-center align-middle"><%= c.getIDCoach()%></th>
                                <td class="text-center"><%= c.getCoachName()%></td>
                                <td class="text-center"><%= c.getGender()%></td>
                                <td class="text-center"><%= c.getPhone()%></td>
                                <td class="text-center"><%= c.getEmail()%></td>
                                <td class="text-center"><%= c.getSpecialization()%></td>
                                <td class="text-center"><%= c.getExperienceYears()%> năm</td>
                                <td class="text-center">
                                    <span class="badge <%= "1".equals(c.getStatus()) ? "badge-success" : "badge-danger"%>">
                                        <%= "1".equals(c.getStatus()) ? "Hoạt động" : "Đã xóa"%>
                                    </span>
                                </td>
                                <td class="text-center table-actions">
                                    <div class="inner-form-action d-flex mt-2">
                                        <form action="EditCoachServlet" method="get">
                                            <input type="hidden" name="id" value="<%= c.getIDCoach()%>"/>
                                            <button type="submit" class="btn btn-sm btn-info" title="Sửa">
                                                <i class="fas fa-edit"></i>
                                            </button>
                                        </form>
                                        <form action="UpdateCoachStatusServlet" method="post">
                                            <input type="hidden" name="id" value="<%= c.getIDCoach()%>"/>
                                            <input type="hidden" name="action" value="<%= "1".equals(c.getStatus()) ? "delete" : "restore"%>"/>
                                            <button type="submit" class="btn btn-sm <%= "1".equals(c.getStatus()) ? "btn-danger" : "btn-warning"%>" title="<%= "1".equals(c.getStatus()) ? "Xóa" : "Khôi phục"%>">
                                                <i class="fas <%= "1".equals(c.getStatus()) ? "fa-trash-alt" : "fa-undo"%>"></i>
                                            </button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                            <% }
                            } else { %>
                            <tr>
                                <td colspan="9" class="text-center">Không có dữ liệu.</td>
                            </tr>
                            <% }%>
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
