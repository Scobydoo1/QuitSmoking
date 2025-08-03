
<%-- 
    Document   : coachViewMemberProgress
    Created on : Jul 3, 2025
    Author     : Nguyen Tien Dat
--%>

<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="DTO.ProgressLog"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.reflect.Method"%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Tiến trình của thành viên</title>

        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- CSS riêng -->
        <link rel="stylesheet" href="css/viewProgressLog.css">
    </head>
    <body>
        <style>
            /* Home Button */
            .home-button {
                position: fixed; /* C? ??nh v? trí */
                top: 20px; /* Cách mép trên 20px */
                left: 20px; /* Cách mép trái 20px */
                background-color: #28a745; /* Màu xanh lá ch? ??o */
                color: white; /* Màu ch? */
                padding: 10px 20px; /* Kho?ng cách bên trong nút */
                font-size: 16px; /* Kích th??c ch? */
                font-weight: bold; /* Ch? ??m */
                border: none; /* Không vi?n */
                border-radius: 8px; /* Bo góc */
                text-decoration: none; /* Lo?i b? g?ch chân */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* ?? bóng nh? */
                transition: all 0.3s ease; /* Hi?u ?ng chuy?n ??ng */
                z-index: 999; /* Hi?n th? trên cùng */
            }


            .home-button:hover {
                background-color: #218838; /* Màu ??m h?n khi hover */
                box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2); /* ?? bóng t?ng khi hover */
                transform: scale(1.05); /* Hi?u ?ng phóng to nh? */
                text-decoration: none;
            }

        </style>
        <a href="homepage.jsp" class="home-button">Home</a>

        <div class="container my-5">
            <h2 class="text-center text-success mb-4">
                Tiến trình của thành viên: <%= request.getAttribute("idMember")%>
            </h2>

            <%
                List<ProgressLog> logs = (List<ProgressLog>) request.getAttribute("logs");
                if (logs == null || logs.isEmpty()) {
            %>
            <div class="alert alert-warning text-center">Không có tiến trình nào.</div>
            <%
            } else {
                for (ProgressLog log : logs) {
            %>
            <div class="log-block shadow-sm p-4 mb-5 bg-white rounded">
                <div class="mb-3"><strong>Loại:</strong> <%= log.getType()%></div>
                <div class="mb-3"><strong>Ngày bắt đầu:</strong> <%= log.getStartDate()%></div>
                <div class="mb-3"><strong>Ngày kết thúc:</strong> <%= log.getEndDate()%></div>
                <div class="mb-3"><strong>Ghi chú tiến trình:</strong>
                    <p class="form-text text-body"><%= log.getProgress() != null ? log.getProgress() : "(Trống)"%></p>
                </div>

                <hr>

                <%
                    for (int i = 1; i <= 5; i++) {
                        Method getQ = ProgressLog.class.getMethod("getQs" + i);
                        Method getA = ProgressLog.class.getMethod("getAs" + i);
                        String question = (String) getQ.invoke(log);
                        String answer = (String) getA.invoke(log);

                        if (question != null && !question.trim().isEmpty()) {
                %>
                <div class="mb-4">
                    <label class="form-label"><strong>Câu hỏi <%= i%>:</strong> <%= question%></label>
                        <textarea class="form-control bg-light" rows="2" readonly><%="submit".equalsIgnoreCase(log.getStatus()) && answer != null ? answer : ""%></textarea>
                </div>
                <%
                        }
                    }
                %>

                <% if ("submit".equalsIgnoreCase(log.getStatus())) {%>
                <form action="GradeProgressServlet" method="post" class="mt-3">
                    <input type="hidden" name="idLog" value="<%= log.getIdLog()%>">
                    <div class="mb-3">
                        <label for="point_<%= log.getIdLog()%>" class="form-label">Chấm điểm:</label>
                        <input type="number" class="form-control" id="point_<%= log.getIdLog()%>" name="point" 
                               value="<%= log.getPoint()%>" min="0" max="100" required>
                    </div>
                    <button type="submit" class="btn btn-success">💾 Lưu điểm</button>
                </form>
                <% } %>
            </div>
            <%
                    }
                }
            %>
        </div>

    </body>
</html>