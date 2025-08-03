

<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="DTO.ProgressLog"%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Xem tiến trình</title>

        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- File CSS riêng -->
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

        <div class="container progress-container">
            <%
                ProgressLog log = (ProgressLog) request.getAttribute("log");
                if (log == null) {
            %>
            <div class="alert alert-danger mt-4">Không tìm thấy dữ liệu nhật ký.</div>
            <a href="progressList.jsp" class="btn btn-outline-success mt-3">⬅ Quay lại danh sách</a>
            <%
            } else {
            %>
            <h2 class="text-center mb-4">Xem chi tiết tiến trình</h2>

            <div class="mb-3">
                <strong>Loại:</strong> <%= log.getType()%>
            </div>
            <div class="mb-3">
                <strong>Ngày bắt đầu:</strong> <%= log.getStartDate()%>
            </div>
            <div class="mb-3">
                <strong>Ngày kết thúc:</strong> <%= log.getEndDate()%>
            </div>

            <%
                String progress = log.getProgress();
                if (progress != null && !progress.trim().isEmpty()) {
            %>
            <div class="mb-4">
                <label class="form-label"><strong>Ghi chú tiến trình:</strong></label>
                <textarea readonly class="form-control bg-light" rows="4"><%= progress%></textarea>
            </div>
            <%
                }
            %>

            <hr>

            <%
                for (int i = 1; i <= 5; i++) {
                    String question = (String) ProgressLog.class.getMethod("getQs" + i).invoke(log);
                    String answer = log.getAnswer(i);
                    if (question != null && !question.trim().isEmpty()) {
            %>
            <div class="mb-4">
                <label class="form-label"><strong>Câu hỏi <%= i%>:</strong> <%= question%></label>
                <textarea readonly class="form-control bg-light" rows="3"><%= answer != null ? answer : ""%></textarea>
            </div>
            <%
                    }
                }
            %>

            <a href="progressList.jsp" class="btn btn-success">⬅ Quay lại danh sách</a>
            <%
                }
            %>
        </div>

    </body>
</html>
