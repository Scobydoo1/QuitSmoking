/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.member;

import DAO.MemberDao;
import DAO.NotificationDao;
import DAO.QuizDao;
import DAO.QuizResultDao;
import DTO.Member;
import DTO.Quiz;
import DTO.QuizResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyen Tien Dat
 */
public class TestServlet extends HttpServlet {

    private int calculateScore(Quiz quiz, String answer) {
        boolean hasC = quiz.getAnswerC() != null;
        boolean hasD = quiz.getAnswerD() != null;

        if (hasC && hasD) {
            // Câu có đủ 4 đáp án
            if (answer.trim().equals(quiz.getAnswerA())) {
                return 3;
            }
            if (answer.trim().equals(quiz.getAnswerB())) {
                return 2;
            }
            if (answer.trim().equals(quiz.getAnswerC())) {
                return 1;
            }
            if (answer.trim().equals(quiz.getAnswerD())) {
                return 0;
            }
        } else {
            // Câu chỉ có A và B
            if (answer.trim().equals(quiz.getAnswerA())) {
                return 1;
            }
            if (answer.trim().equals(quiz.getAnswerB())) {
                return 0;
            }
        }
        return 0;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        try {
//            QuizDao dao = new QuizDao();
//
//            request.setAttribute("quizList", dao.getAllQuiz());
//            request.getRequestDispatcher("test.jsp").forward(request, response);
//        } catch (Exception e) {
//        }

        try {
            HttpSession session = request.getSession();
            String idMember = (String) session.getAttribute("id");

            if (idMember == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            MemberDao memberDao = new MemberDao();
            Member member = memberDao.getMemberById(idMember);  // cần có hàm này
            int point = member.getPoint();  // giả sử member không null

            if (point >= 0) {
                request.setAttribute("score", point);
                request.setAttribute("hasSubmitted", true);
            } else {
                QuizDao quizDao = new QuizDao();
                List<Quiz> quizList = quizDao.getAllQuiz();

                request.setAttribute("quizList", quizList);
                request.setAttribute("hasSubmitted", false);
            }

            request.getRequestDispatcher("test.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi xảy ra: " + e.getMessage());
            request.getRequestDispatcher("test.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        NotificationDao noti=new NotificationDao();
        try {
            HttpSession session = request.getSession();
            String idMember = (String) session.getAttribute("id");

            if (idMember == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            QuizDao quizDao = new QuizDao();
            List<Quiz> quizList = quizDao.getAllQuiz();
            QuizResultDao resultDao = new QuizResultDao();
            MemberDao memberDao = new MemberDao();

            int totalScore = 0;

            for (Quiz quiz : quizList) {
                String quizId = quiz.getIDQuiz();
                String answer = request.getParameter("answer_" + quizId);

                if (answer != null) {
                    int score = calculateScore(quiz, answer);
                    totalScore += score;

                    resultDao.saveResult(new QuizResult(idMember, quizId, answer, java.sql.Date.valueOf(LocalDate.now())));
                }
            }

            // ✅ Cập nhật điểm
            memberDao.updatePoint(idMember, totalScore);
            noti.sendNotificationToMember("NT18", idMember);
            
            doGet(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
            request.getRequestDispatcher("test.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
