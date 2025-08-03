
<%@page import="java.util.List"%>
<%@page import="DTO.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Test</title>

        <link href="css/stylehomepage.css" rel="stylesheet" type="text/css"/>
        <link href="css/styletest.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <%@include file="information/bootstrap.jspf" %>
    </head>
    <body>
        <!-- Header -->
        <div>
            <%@include file="information/header.jspf" %>
        </div>


        <h2>Quiz Test</h2>

        <%        Boolean hasSubmitted = (Boolean) request.getAttribute("hasSubmitted");
        %>

        <div class="container">
            <% if (hasSubmitted != null && hasSubmitted) {%>
            <!-- ✅ Hiển thị kết quả -->
            <div class="results-container">
                <div class="results-icon">
                    <i class="fas fa-trophy"></i>
                </div>

                <h3 class="results-title">Kết quả bài kiểm tra</h3>

                <p class="completion-message">
                    Bạn đã hoàn thành bài kiểm tra đánh giá mức độ phụ thuộc thuốc lá!
                </p>

                <div class="score-display">
                    <span class="score-label">Mức độ của bạn là:</span>
                    <span class="score-value"><%= request.getAttribute("score")%></span>
                </div>
                <p class="completion-message">
                    Nếu kết quả là 0-4, bạn nên chọn khóa <strong>Silver</strong>.<br>
                    Nếu kết quả là 5-8, bạn nên chọn khóa <strong>Gold</strong>.<br>
                    Nếu kết quả là 9-12, bạn nên chọn khóa <strong>Diamond</strong>.
                </p>

                <div class="action-buttons">
                    <form action="RetakeTestServlet" method="post" style="display: inline;">
                        <button type="submit" class="btn-retake">
                            <i class="fas fa-redo"></i> Làm lại bài kiểm tra
                        </button>
                    </form>

                    <a href="homepage.jsp" class="btn-home">
                        <i class="fas fa-home"></i> Về trang chủ
                    </a>
                </div>
            </div>
            <% } else { %>
            <!-- ✅ Hiển thị bài kiểm tra nếu chưa nộp -->
            <%
                List<Quiz> quizList = (List<Quiz>) request.getAttribute("quizList");

                if (quizList == null || quizList.isEmpty()) {
            %>
            <p style="text-align:center;">Không có câu hỏi nào để hiển thị.</p>
            <%
            } else {
            %>
            <form action="TestServlet" method="post">
                <%
                    int qNo = 1;
                    for (Quiz q : quizList) {
                %>
                <div class="question-block">
                    <p><strong>Câu hỏi <%= qNo++%>:</strong> <%= q.getQuestion()%></p>
                    <input type="hidden" name="quizID" value="<%= q.getIDQuiz()%>" />

                    <% if (q.getAnswerA() != null) {%>
                    <div class="answer-option">
                        <label><input type="radio" name="answer_<%= q.getIDQuiz()%>" value="<%= q.getAnswerA()%>" required /> A. <%= q.getAnswerA()%></label>
                    </div>
                    <% } %>

                    <% if (q.getAnswerB() != null) {%>
                    <div class="answer-option">
                        <label><input type="radio" name="answer_<%= q.getIDQuiz()%>" value="<%= q.getAnswerB()%>" /> B. <%= q.getAnswerB()%></label>
                    </div>
                    <% } %>

                    <% if (q.getAnswerC() != null) {%>
                    <div class="answer-option">
                        <label><input type="radio" name="answer_<%= q.getIDQuiz()%>" value="<%= q.getAnswerC()%>" /> C. <%= q.getAnswerC()%></label>
                    </div>
                    <% } %>

                    <% if (q.getAnswerD() != null) {%>
                    <div class="answer-option">
                        <label><input type="radio" name="answer_<%= q.getIDQuiz()%>" value="<%= q.getAnswerD()%>" /> D. <%= q.getAnswerD()%></label>
                    </div>
                    <% } %>
                </div>
                <hr/>
                <%
                    }
                %>
                
                
                <% } %>

                <div style="text-align: center;">
                    <button type="submit" class="btn-submit">Nộp bài</button>
                </div>
            </form>
            <%
                } // đóng if quizList
            %>
            
        </div>

        <!-- Footer -->
        <%@include file="information/footer.jspf" %>
    </body>
</html>

