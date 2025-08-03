/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.QuizResult;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Tien Dat
 */
public class QuizResultDao {

    public void saveResult(QuizResult result) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.getConnection();
        String sql = "INSERT INTO QuizResult (IDMember, IDQuiz, Answer, DateSubmit) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, result.getIDMember());
        ps.setString(2, result.getIDQuiz());
        ps.setString(3, result.getAnswer());
        ps.setDate(4, result.getDateSubmit());
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public boolean hasSubmitted(String idMember) throws Exception {
        String sql = "SELECT COUNT(*) FROM QuizResult WHERE IDMember = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idMember);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public void deleteResultsByMember(String idMember) throws Exception {
        String sql = "DELETE FROM QuizResult WHERE IDMember = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idMember);
            ps.executeUpdate();
        }
    }

    public int calculateScore(String idMember) throws Exception {
        String sql = "SELECT IDQuiz, Answer FROM QuizResult WHERE IDMember = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idMember);
            ResultSet rs = ps.executeQuery();
            int total = 0;
            while (rs.next()) {
                String answer = rs.getString("Answer").trim();
                if (answer.equals("A")) {
                    total += 3;
                } else if (answer.equals("B")) {
                    total += 2;
                } else if (answer.equals("C")) {
                    total += 1;
                }
                // D hoặc null = 0 điểm
            }
            return total;
        }
    }
}
