
<%-- 
    Document   : CreateQuestion
    Created on : Jun 30, 2025, 12:49:17 PM
    Author     : Nghia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Tạo câu hỏi cho thành viên</title>
        <link rel="stylesheet" href="css/create-question.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <%@include file="information/bootstrap.jspf" %>

        <link href="css/stylehomepage.css" rel="stylesheet" type="text/css"/>


        <%
            // Lấy idMember từ request (GET)

            String idMember = request.getParameter("idMember");
            if (idMember == null) {
                idMember = "";
            }
        %>
    </head>
    <body>
        <%@include file="information/header.jspf" %>    
        <script>
            document.addEventListener('DOMContentLoaded', () => {
                const container = document.getElementById('questions');
                const hiddenCount = document.getElementById('questionCount');
                const spanDisplay = document.getElementById('countDisplay');
                const MAX_Q = 10;

                function updateCount() {
                    const n = container.querySelectorAll('.question-item').length;
                    hiddenCount.value = n;
                    spanDisplay.textContent = n;
                }

                function renumber() {
                    container.querySelectorAll('.question-item').forEach((item, idx) => {
                        const num = idx + 1;
                        const label = item.querySelector('label');
                        const input = item.querySelector('input');
                        const btn = item.querySelector('button');

                        label.htmlFor = 'qs' + num;
                        label.textContent = 'Câu hỏi ' + num + ':';
                        input.id = 'qs' + num;
                        input.name = 'qs' + num;
                        input.placeholder = 'Nhập câu hỏi ' + num;
                        btn.setAttribute('data-index', num);
                    });
                }

                // Thêm câu hỏi mới
                window.addQuestion = () => {
                    if (container.children.length >= MAX_Q)
                        return;
                    const div = document.createElement('div');
                    div.className = 'question-item';
                    div.innerHTML = `
                <label></label>
                <input type="text" />
                <button type="button">×</button>
              `;
                    div.querySelector('button').addEventListener('click', () => {
                        div.remove();
                        renumber();
                        updateCount();
                    });
                    container.appendChild(div);
                    renumber();
                    updateCount();
                };

                // Khởi tạo 1 câu hỏi mặc định
                addQuestion();
            });
        </script>



        <!-- FORM -->
        <div class="main-container">
            <div class="form-card">
                <div class="form-title-row">
                    <div class="form-title-left">
                        <span class="icon-circle"><i class="fas fa-question"></i></span>
                        <span class="form-title-text">Nhập tiến trình Cho Thành Viên:</span>
                    </div>
                    <span class="member-id"><%= idMember%></span>
                </div>
                <div class="desc">
                    <span class="count-badge" id="countBadge"><span id="countDisplay">1</span>/10</span> câu hỏi
                </div>
                <form method="get" action="SaveQuestionsServlet" class="question-form" autocomplete="off">
                    <div class="progress-section">
                        <label for="progress">Nhập tiến trình:</label>
                        <textarea
                            id="progress"
                            name="progress"
                            rows="5"
                            style="width:100%;"
                            placeholder="Mô tả ngắn gọn tiến trình làm việc…"
                            required
                            ></textarea>
                    </div>
                    <input type="hidden" name="idMember" value="<%= idMember%>"/>
                    <div class="plan-section">
                        <span class="plan-label">Chọn thời hạn (ngày):</span>
                        <label class="plan-radio">
                            <input type="radio" id="plan3" name="planDays" value="3" required>
                            <span class="plan-custom"></span>
                            3 ngày
                        </label>
                        <label class="plan-radio">
                            <input type="radio" id="plan5" name="planDays" value="5">
                            <span class="plan-custom"></span>
                            5 ngày
                        </label>
                        <label class="plan-radio">
                            <input type="radio" id="plan7" name="planDays" value="7">
                            <span class="plan-custom"></span>
                            7 ngày
                        </label>
                    </div>
                    <input type="hidden" id="questionCount" name="questionCount" value="1"/>
                    <div id="questions"></div>
                    
                    <button type="button" class="btn add-btn" id="addBtn">
                        <i class="fas fa-plus"></i> Thêm câu hỏi
                    </button>
                    <button type="submit" class="btn submit-btn">
                        <i class="fas fa-database"></i> Gửi tiến trình
                    </button>
                </form>
            </div>
        </div>
        <script src="js/create-question.js"></script>
    </body>
</html>

