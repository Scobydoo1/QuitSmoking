
<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="information/bootstrap.jspf" %>
        <!-- Link CSS-->
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <!--Home Page-->
        <a href="homepage.jsp" class="home-button">Trang chủ</a>    
        <!--End Home Page-->

        <!--Form Login-->
        <div class="login">
            <div class="container">
                <div class="row d-flex align-items-stretch" style="min-height: 500px;">
                    <div class="col-xl-7">
                        <div class="inner-login">
                            <div class="inner-box">
                                <c:if test="${not empty param.message}">
                                    <div class="alert alert-success">${param.message}</div>
                                </c:if>
                                <!--Display Error-->
                                <c:if test="${not empty error}">
                                    <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Login Error</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body text-danger font-weight-bold">
                                                    ${error}
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-success" data-dismiss="modal">OK</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>                                 
                                </c:if>
                                <!--End Display Error-->

                                <c:if test="${not empty message}">
                                    <div class="alert alert-success" role="alert">
                                        ${message}
                                    </div>
                                </c:if>
                                <h3 class="inner-title">Đăng Nhập</h3>
                                <form action="LoginServlet" method="POST" class="form-login">
                                    <label for="role" class="item-name">Vai trò</label><br>
                                    <div class="select-wrapper">
                                        <select class="inner-option" id="role" name="role">
                                            <option value="member" selected>Member</option>
                                            <option value="coach">Coach</option>
                                        </select>
                                    </div>


                                    <label for="IDMember" class="item-name">ID Member</label><br>
                                    <div class="inner-input">
                                        <input type="text" id="IDMember" name="username">
                                    </div>

                                    <label for="password" class="item-name">Mật Khẩu</label><br>
                                    <div class="inner-input">
                                        <input type="password" id="password" name="password">
                                    </div>

                                    <div class="inner-button">
                                        <button type="submit" class="button" value="Login">Đăng Nhập</button>
                                    </div>

                                    <!-- Remember Me + Forgot Password -->
                                    <div class="inner-options">
                                        <label for="rememberMe" class="item-name">
                                            <input type="checkbox" id="rememberMe" name="remember" value="on">
                                            Lưu đăng nhập
                                        </label>
                                        <a href="ForgotPasswordServlet">Quên mật khẩu?</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-5">
                        <div class="welcome">
                            <div class="inner-welcome">
                                <h2 class="inner-title-welcome">
                                    Chào mừng bạn đến với trang đăng nhập
                                </h2>
                                <span class="inner-sub-title">Bạn chưa có tài khoản?</span>
                                <div class="inner-button">
                                    <a href="register.jsp" class="button">Đăng ký ngay</a>
                                </div>
                                <div class="inner-button-admin">
                                    <a href="adminLogin.jsp" class="button">Đăng nhập Admin</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--End Form Login-->

        <c:if test="${not empty error}">
            <script>
                $(document).ready(function () {
                    $('#errorModal').modal('show');
                    $('#errorModal').on('hidden.bs.modal', function () {
                        $('#IDMember').focus();
                    });
                });
            </script>
        </c:if>
    </body>

