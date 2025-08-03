<%@page import="java.util.List"%>
<%@page import="DTO.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý Member</title>
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
                <h1 class="mb-4 mt-4">Danh sách Member</h1>
                <form action="AdminSearchMemberServlet" method="get" class="mb-4">
                    <div class="input-group">
                        <input type="text" class="form-control" name="keyword" placeholder="Nhập tên Member..." value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : ""%>">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-success"><i class="fas fa-search"></i> Tìm kiếm</button>
                        </div>
                    </div>
                </form>

                <div class="table-responsive">
                    <table class="table table-hover table-bordered bg-white">
                        <thead class="bg-success text-white">
                            <tr>
                                <th>ID</th>
                                <th>Tên</th>
                                <th>Giới tính</th>
                                <th>Điện thoại</th>
                                <th>Email</th>
                                <th>ID Coach</th>
                                <th>Trạng thái</th>
                                <th class="text-center">Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% List<Member> members = (List<Member>) request.getAttribute("members");
                                    if (members != null && !members.isEmpty()) {
                                        for (Member m : members) {
                                            String id = m.getIDMember();%>
                            <tr>
                                <td><%= id%></td>
                                <td><%= m.getMemberName()%></td>
                                <td><%= m.getGender()%></td>
                                <td><%= m.getPhone()%></td>
                                <td><%= m.getEmail()%></td>
                                <td><%= m.getIDCoach()%></td>
                                <td class="text-center"><span class="badge <%= "1".equals(m.getStatus()) ? "badge-success" : "badge-danger"%>"><%= "1".equals(m.getStatus()) ? "Hoạt động" : "Đã xóa"%></span></td>
                                <td class="text-center table-actions">
                                    <a href="AdminUpdateMemberServlet?IDMember=<%= id%>" class="btn btn-sm btn-info" title="Sửa"><i class="fas fa-edit"></i></a>
                                        <% if ("1".equals(m.getStatus())) {%>
                                    <form action="AdminDeleteMemberServlet" method="post" onsubmit="return confirm('Xác nhận xoá member này?');" class="d-inline">
                                        <input type="hidden" name="IDMember" value="<%= id%>"/>
                                        <button type="submit" class="btn btn-sm btn-danger" title="Xóa"><i class="fas fa-trash-alt"></i></button>
                                    </form>
                                    <% } else if ("2".equals(m.getStatus())) {%>
                                    <form action="AdminManageMemberServlet" method="post" onsubmit="return confirm('Xác nhận khôi phục member này?');" class="d-inline">
                                        <input type="hidden" name="IDMember" value="<%= id%>"/>
                                        <button type="submit" class="btn btn-sm btn-warning" title="Khôi phục"><i class="fas fa-undo"></i></button>
                                    </form>
                                    <% } %>
                                </td>
                            </tr>
                            <% }
                                } else { %>
                            <tr>
                                <td colspan="8" class="text-center">Không có dữ liệu.</td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
                <a href="adminDashboard.jsp" class="btn btn-secondary mb-2">
                    <i class="fas fa-arrow-left"></i> Trở về trang chủ
                </a>
            </div>
        </div>
        <!-- /Main Content -->
</body>
</html>