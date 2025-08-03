<%@page import="DTO.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật Member</title>
        <%@include file="information/bootstrap.jspf" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
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
                <% Member member = (Member) request.getAttribute("member");
                    if (member == null) { %>
                <div class="alert alert-danger">Không tìm thấy Member để cập nhật.</div>
                <% } else {%>
                <div class="card">
                    <div class="card-header bg-success text-white">
                        <h4 class="mb-0">Cập nhật Member: <%= member.getIDMember()%></h4>
                    </div>
                    <div class="card-body">
                        <form action="AdminUpdateMemberServlet" method="post">
                            <input type="hidden" name="IDMember" value="<%= member.getIDMember()%>" />
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="password">Password</label>
                                    <input type="text" class="form-control" id="password" name="password" value="<%= member.getPassword()%>">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="memberName">Member Name</label>
                                    <input type="text" class="form-control" id="memberName" name="memberName" value="<%= member.getMemberName()%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Gender</label>
                                <div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender" id="male" value="Male" <%= "Male".equals(member.getGender()) ? "checked" : ""%>>
                                        <label class="form-check-label" for="male">Male</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender" id="female" value="Female" <%= "Female".equals(member.getGender()) ? "checked" : ""%>>
                                        <label class="form-check-label" for="female">Female</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="phone">Phone</label>
                                    <input type="text" class="form-control" id="phone" name="phone" value="<%= member.getPhone()%>">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" value="<%= member.getEmail()%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address">Address</label>
                                <input type="text" class="form-control" id="address" name="address" value="<%= member.getAddress()%>">
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="dateOfBirth">Date of Birth</label>
                                    <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="<%= member.getDateOfBirth()%>">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="joinDate">Join Date</label>
                                    <input type="date" class="form-control" id="joinDate" name="joinDate" value="<%= member.getJoinDate()%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="image">Image URL</label>
                                <input type="text" class="form-control" id="image" name="image" value="<%= member.getImage()%>">
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="point">Point</label>
                                    <input type="number" class="form-control" id="point" name="point" value="<%= member.getPoint()%>">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="IDCoach">ID Coach</label>
                                    <input type="text" class="form-control" id="IDCoach" name="IDCoach" value="<%= member.getIDCoach()%>">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="status">Status</label>
                                    <input type="text" class="form-control" id="status" name="status" value="<%= member.getStatus()%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="subcription">Subscription</label>
                                <input type="text" class="form-control" id="subcription" name="subcription" value="<%= member.getSubscription()%>">
                            </div>
                            <button type="submit" class="btn btn-success    "><i class="fas fa-save"></i> Lưu thay đổi</button>
                            <a href="AdminManageMemberServlet" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Hủy</a>
                        </form>
                    </div>
                </div>
                <% }%>
            </div>
        </div>
        <!-- /Main Content -->

    </body>
</html>
