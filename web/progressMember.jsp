<%-- 
    Document   : progressMember
    Created on : Jun 26, 2025, 9:30:49 AM
    Author     : Nguyen Tien Dat
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, DTO.ProgressLog" %>

<html>
<head>
    <title>Tiến trình bỏ thuốc</title>
    <link rel="stylesheet" type="text/css" href="css/progressMember.css">
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
    <h2>Tiến trình bỏ thuốc của bạn</h2>

    <%
        List<ProgressLog> logList = (List<ProgressLog>) request.getAttribute("logList");
        if (logList != null && !logList.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>Ngày</th>
                <th>Số điếu hút</th>
                <th>Câu trả lời</th>
            </tr>
            <% for (ProgressLog log : logList) { %>
            <tr>
                <td><%= log.getLogDate() %></td>
                <td><%= log.getNumberOfCigarettes() %></td>
                <td>
                    <%
                        String notes = log.getNotes();
                        if (notes != null && !notes.isEmpty()) {
                            // Dùng regex để tách các câu theo định dạng "Câu 1:", "Câu 2:"
                            String regex = "(Câu \\d+:.*?)(?=Câu \\d+:|$)";
                            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex, java.util.regex.Pattern.DOTALL);
                            java.util.regex.Matcher matcher = pattern.matcher(notes);

                            while (matcher.find()) {
                    %>
                        <div class="note-item"><%= matcher.group(1).trim() %></div>
                    <%
                            }
                        } else {
                    %>
                        <div class="note-item">Không có câu trả lời</div>
                    <% } %>
                </td>
            </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>Chưa có tiến trình nào được ghi nhận.</p>
    <% } %>
</body>
</html>