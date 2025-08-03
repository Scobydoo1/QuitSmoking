<%-- 
    Document   : error
    Created on : Jul 3, 2025, 8:44:09 AM
    Author     : Nguyen Tien Dat
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lỗi hệ thống</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 40px; background-color: #fef2f2; }
        .error-box {
            background-color: #ffe6e6;
            border-left: 5px solid red;
            padding: 20px;
            max-width: 600px;
            margin: auto;
            box-shadow: 0 0 10px rgba(255, 0, 0, 0.1);
        }
        .error-box h2 {
            color: red;
        }
        .back-link {
            margin-top: 20px;
            display: block;
        }
    </style>
</head>
<body>

<div class="error-box">
    <h2>⚠️ Đã xảy ra lỗi</h2>
    <p>
        <%
            String message = (String) request.getAttribute("errorMessage");
            if (message == null) {
                message = (String) request.getAttribute("error");
            }
            if (message == null) {
                message = "Lỗi không xác định. Vui lòng thử lại sau.";
            }
        %>
        <%= message %>
    </p>
    <a class="back-link" href="javascript:history.back()">⬅ Quay lại trang trước</a>
</div>

</body>
</html>
