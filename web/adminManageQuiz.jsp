<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="DTO.Quiz" %>
<%@ page import="DAO.QuizDao" %>

<%-- Check for admin session --%>
<% if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    } %>

<%
    QuizDao dao = new QuizDao();
    List<Quiz> quizList = dao.getAllQuiz();
%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Quản lý Câu hỏi Quiz</title>
        <%@include file="information/bootstrap.jspf" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            .table-actions .btn {
                margin-right: 5px;
            }
            .table-bordered th,
            .table-bordered td {
                border: 1px solid #cccccc;
            }
            
            input:focus,
            select:focus,
            textarea:focus,
            .form-control:focus {
                outline: none;
                box-shadow: none;
                border-color: #ced4da; /* hoặc đổi thành màu bạn muốn */
            }

            body {
                background: #f0fff4; 
            }
        </style>
    </head>
    <body>
            <!-- /Sidebar -->

            <!-- Main Content -->
            <div class="main-content d-flex justify-content-center align-items-center" style="min-height: 100vh;">
                <div class="container">
                    <h2 class="h2 mb-4">Quản lý câu hỏi Quiz</h2>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead class="bg-success text-white">
                                <tr>
                                    <th>ID</th>
                                    <th>Câu hỏi</th>
                                    <th>Đáp án A</th>
                                    <th>Đáp án B</th>
                                    <th>Đáp án C</th>
                                    <th>Đáp án D</th>
                                    <th style="width: 120px;">Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Quiz q : quizList) {%>
                            <form action="UpdateQuizServlet" method="post">
                                <tr>
                                    <td><input type="text" name="idQuiz" class="form-control" value="<%= q.getIDQuiz()%>" readonly></td>
                                    <td><textarea name="question" class="form-control" rows="3"><%= q.getQuestion()%></textarea></td>
                                    <td><textarea name="answerA" class="form-control" rows="2"><%= q.getAnswerA()%></textarea></td>
                                    <td><textarea name="answerB" class="form-control" rows="2"><%= q.getAnswerB()%></textarea></td>
                                    <td><textarea name="answerC" class="form-control" rows="2"><%= q.getAnswerC() != null ? q.getAnswerC() : ""%></textarea></td>
                                    <td><textarea name="answerD" class="form-control" rows="2"><%= q.getAnswerD() != null ? q.getAnswerD() : ""%></textarea></td>
                                    <td>
                                        <button type="submit" class="btn btn-success btn-block"><i class="fas fa-save"></i> Cập nhật</button>
                                    </td>
                                </tr>
                            </form>
                            <% }%>
                            </tbody>
                        </table>
                    </div>
                    <a href="adminDashboard.jsp" class="btn btn-secondary mt-3">
                        <i class="fas fa-arrow-left"></i> Trở về trang chủ
                    </a>
                </div>
            </div>
            <!-- /Main Content -->
        </div>
    </body>
</html>
