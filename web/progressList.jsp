
<%@page import="DAO.ProgressLogDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="DTO.ProgressLog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String idMember = (String) session.getAttribute("id");
    if (idMember == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<ProgressLog> logs = new ArrayList<>();
    try {
        ProgressLogDAO dao = new ProgressLogDAO();
        logs = dao.getProgressLogsByMember(idMember);
    } catch (Exception e) {
        out.println("<p class='text-danger'>Lỗi: " + e.getMessage() + "</p>");
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Danh sách nhật ký tiến trình</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Link đến file CSS riêng -->
        <link rel="stylesheet" href="css/progressList.css">
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
            <h2 class="text-center">Danh sách nhật ký tiến trình</h2>

            <div class="table-responsive">
                <table class="table table-bordered text-center align-middle">
                    <thead>
                        <tr>
                            <th>Ngày bắt đầu</th>
                            <th>Ngày kết thúc</th>
                            <th>Loại</th>
                            <th>Trạng thái</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (ProgressLog log : logs) {
                        %>
                        <tr>
                            <td><%= log.getStartDate()%></td>
                            <td><%= log.getEndDate()%></td>
                            <td><%= log.getType()%></td>
                            <td>
                                <% if ("save".equalsIgnoreCase(log.getStatus())) {%>
                                <form action="SubmitProgressLogServlet" method="get" class="d-inline">
                                    <input type="hidden" name="idLog" value="<%= log.getIdLog()%>" />
                                    <button type="submit" class="btn btn-green">✏️ Trả lời</button>
                                </form>
                                <% } else {%>
                                <form action="ViewProgressLogServlet" method="get" class="d-inline">
                                    <input type="hidden" name="idLog" value="<%= log.getIdLog()%>" />
                                    <button type="submit" class="btn btn-green">👁️ Xem</button>
                                </form>
                                <% } %>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

    </body>
</html>

