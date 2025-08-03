<%@page import="DTO.Member"%>
<%@page import="DTO.BlogPost"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Blog Manager</title>
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
            .table img {
                max-width: 100px;
                height: auto;
                display: block;
                margin: 0 auto;
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
                    <h1 class="h2 mb-2 text-black">Quản lý Bài viết Blog</h1>
                </div>

                <%
                    // Lấy danh sách bài viết và thành viên từ request
                    List<BlogPost> blogPosts = (List<BlogPost>) request.getAttribute("blogPosts");
                    List<Member> members = (List<Member>) request.getAttribute("members");

                    // Nếu có lỗi trong việc lấy dữ liệu, hiển thị thông báo lỗi
                    String error = (String) request.getAttribute("error");
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
                                <th>ID Bài viết</th>
                                <th>Tên Thành viên</th>
                                <th>Tiêu đề</th>
                                <th>Nội dung</th>
                                <th>Ngày đăng</th>
                                <th>Hình ảnh</th>
                                <th class="text-center">Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                // Duyệt qua các bài viết để hiển thị thông tin
                                if (blogPosts != null && !blogPosts.isEmpty()) {
                                    for (BlogPost post : blogPosts) {
                                        String memberId = post.getIdMember();
                                        String memberName = "Unknown";

                                        // Duyệt qua danh sách thành viên để tìm tên thành viên tương ứng
                                        if (members != null) {
                                            for (Member member : members) {
                                                if (member.getIDMember().equals(memberId)) {
                                                    memberName = member.getMemberName();  // Gán tên thành viên nếu tìm thấy
                                                    break;
                                                }
                                            }
                                        }
                            %>
                            <tr>
                                <td><%= post.getIdPost()%></td> <!-- Hiển thị ID bài viết -->
                                <td><%= memberName%></td> <!-- Hiển thị tên thành viên -->
                                <td><%= post.getTitle()%></td> <!-- Hiển thị tiêu đề bài viết -->
                                <td><%= post.getContent()%></td> <!-- Hiển thị nội dung bài viết -->
                                <td><%= post.getPublishDate()%></td>
                                <td>  <img src="images/Blog/<%= post.getImage()%>" alt="Blog Image">
                                </td>

                                <td class="text-center table-actions">
                                    <!-- Nút xóa, truyền ID bài viết vào -->
                                    <form action="AdminBlogViewServlet" method="POST" onsubmit="return confirm('Bạn có chắc chắn muốn xóa bài viết này?');" class="d-inline">
                                        <input type="hidden" name="blogPostId" value="<%= post.getIdPost()%>">
                                        <button type="submit" name="action" value="delete" class="btn btn-sm btn-danger" title="Xóa"><i class="fas fa-trash-alt"></i></button>
                                    </form>
                                </td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="7" class="text-center">Không có bài viết nào.</td>
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
    </body>
</html>
