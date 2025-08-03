<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Reset Password</title>
        <%@include file="information/bootstrap.jspf" %>
        <style>
            .container {
                margin-top: 300px;
            }
            .form-control:focus {
                box-shadow: none;
                border-color: #ced4da;
            }
        </style>
    </head>
    <body class="bg-success">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-5">
                    <div class="card p-4 shadow-sm">
                        <h3 class="mb-4">Reset Your Password</h3>
                        <%
                            String errorMessage = (String) request.getAttribute("error");
                            if (errorMessage != null) {
                        %>
                        <p style="color: red;"><%= errorMessage%></p>
                        <%
                            }
                        %>

                        <form action="ResetPasswordServlet" method="POST">
                            <input type="hidden" name="token" value="<%= request.getAttribute("token")%>" />

                            <div class="mb-3">
                                <label for="newPassword" class="form-label">New Password</label>
                                <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                            </div>

                            <div class="mb-3">
                                <label for="confirmPassword" class="form-label">Confirm Password</label>
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                            </div>

                            <button type="submit" class="btn btn-success w-100" name="btAction" value="UpdatePassword">Update Password</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
