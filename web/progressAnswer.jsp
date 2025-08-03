
<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="DTO.ProgressLog"%>
<%@page import="java.lang.reflect.Method"%>

<html>
    <head>
        <title>Trả lời câu hỏi tiến trình</title>
        <%@include file="information/bootstrap.jspf" %>
        <link rel="stylesheet" type="text/css" href="css/proressAnswer.css">
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
        <%
            ProgressLog log = (ProgressLog) request.getAttribute("log");
            if (log == null) {
        %>
        <div class="container mt-5">
            <div class="alert alert-danger text-center shadow-sm" role="alert">
                Không tìm thấy dữ liệu nhật ký.
            </div>
        </div>
        <%
                return;
            }
        %>

        <div class="container mt-4 mb-5">
            <h2 class="text-center text-success title mb-4">✍️ Trả lời câu hỏi từ huấn luyện viên</h2>

            <% String progress = log.getProgress();
                if (progress != null && !progress.trim().isEmpty()) {%>
            <div class="card border-success mb-4 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title text-success font-weight-bold">
                        <i class="fas fa-sticky-note mr-2"></i> Nhật ký ghi chú:
                    </h5>
                    <textarea class="form-control" rows="4" readonly><%= progress%></textarea>
                </div>
            </div>
            <% }%>

            <form action="SubmitProgressLogServlet" method="post">
                <input type="hidden" name="idLog" value="<%= log.getIdLog()%>">

                <%
                    for (int i = 1; i <= 5; i++) {
                        Method getQs = ProgressLog.class.getMethod("getQs" + i);
                        Method getAs = ProgressLog.class.getMethod("getAs" + i);
                        String question = (String) getQs.invoke(log);
                        String answer = (String) getAs.invoke(log);

                        if (question != null && !question.trim().isEmpty()) {
                %>
                <div class="card mb-3 border-light shadow-sm">
                    <div class="card-body">
                        <div class="card-subtitle mb-2 text-success font-weight-bold">
                            <i class="fas fa-question-circle mr-1"></i> Câu hỏi <%= i%>:
                        </div>
                        <p class="card-text p-3 rounded bg-light border">
                            <%= question%>
                        </p>
                        <textarea name="as<%= i%>" class="form-control mt-2" rows="3"
                                  placeholder="Nhập câu trả lời của bạn..."><%= answer != null ? answer : ""%></textarea>
                    </div>
                </div>
                <%
                        }
                    }
                %>

                <div class="d-flex justify-content-end mt-4">
                    <a href="progressList.jsp" class="btn btn-success">⬅ Quay lại danh sách</a>

                    <button type="submit" name="action" value="save" class="btn btn-success shadow-sm mr-2">
                        <i class="fas fa-save"></i> Lưu
                    </button>
                    <button type="submit" name="action" value="submit" class="btn btn-primary shadow-sm">
                        <i class="fas fa-paper-plane"></i> Gửi
                    </button>
                    
                </div>
                
            </form>
        </div>
    </body>
</html>