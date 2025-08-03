<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <%@include file="information/bootstrap.jspf" %>
        <title>Forgot password</title>
        <style>
            .forgot-password {
                margin: 300px 0;
            }
            .form-control:focus {
                box-shadow: none;
                border-color: #ced4da;
            }      
        </style>
    </head>

    <body class="bg-success">
        <div class="forgot-password">
            <div class="inner-forgot">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-6 col-lg-5">
                            <div class="inner-box rounded-lg p-5 bg-white">
                                <h3 class="inner-title mb-4 text-center">Send to Email</h3>                               
                                <form action="ForgotPasswordServlet" method="POST">
                                    <c:set var="errors" value="${requestScope.ERROR}" />
                                            <c:set var="messages" value="${requestScope.message}" />
                                            <c:if test="${not empty errors}">
                                                <div class="alert alert-danger mb-3 p-1">${errors}</div>
                                            </c:if>
                                            <c:if test="${not empty messages}">
                                                <div class="alert alert-success mb-3 p-1">${messages}</div>
                                            </c:if>
                                    <div class="inner-form mb-3">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="email" name="email" required>
                                    </div>
                                    <div class="text-center">
                                        <button class="btn btn-success w-100">Send</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>

</html>