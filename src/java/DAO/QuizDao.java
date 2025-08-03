/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Utils.DBUtils.getConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.Quiz;
import Utils.DBUtils;
import java.sql.Connection;

/**
 *
 * @author Nghia
 */
public class QuizDao {

    public List<Quiz> getAllQuiz() throws SQLException, ClassNotFoundException {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT IDQuiz, Question, AnswerA, AnswerB, AnswerC, AnswerD FROM Quiz";

        try {
            PreparedStatement pstmt = getConnection().prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setIDQuiz(rs.getString("IDQuiz"));
                quiz.setQuestion(rs.getString("Question"));
                quiz.setAnswerA(rs.getString("AnswerA"));
                quiz.setAnswerB(rs.getString("AnswerB"));
                quiz.setAnswerC(rs.getString("AnswerC"));
                quiz.setAnswerD(rs.getString("AnswerD"));
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    public void insertQuizResult(String idMember, String idQuiz, String answer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO dbo.QuizResult (IDMember, IDQuiz, Answer, DateSubmit) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, idMember);
            ps.setString(2, idQuiz);
            ps.setString(3, answer);

            java.util.Date now = new java.util.Date();
            ps.setDate(4, new java.sql.Date(now.getTime()));

            ps.executeUpdate();
        } catch (SQLException e) {

            throw new SQLException("Error while inserting quiz result: " + e.getMessage(), e);
        }
    }

    public void updateQuiz(Quiz quiz) throws Exception {
        String sql = "UPDATE Quiz SET Question=?, AnswerA=?, AnswerB=?, AnswerC=?, AnswerD=? WHERE IDQuiz=?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, quiz.getQuestion());
            ps.setString(2, quiz.getAnswerA());
            ps.setString(3, quiz.getAnswerB());
            ps.setString(4, quiz.getAnswerC());
            ps.setString(5, quiz.getAnswerD());
            ps.setString(6, quiz.getIDQuiz());
            ps.executeUpdate();
        }
    }

}
