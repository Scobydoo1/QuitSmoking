/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Nguyen Tien Dat
 */
import DTO.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DTO.Support;
import Utils.DBUtils;

public class SupportDAO {
    
    private Connection conn;

    public SupportDAO() {
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    // Lấy tất cả tin nhắn giữa Member và Coach
    public List<Support> getMessagesByMember(String idMember) {
        List<Support> list = new ArrayList<>();
        String sql = "SELECT * FROM Support WHERE IDMember = ? ORDER BY feedbackDate ASC";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idMember);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Support msg = new Support(
                        rs.getInt("IDSupport"),
                        rs.getString("IDMember"),
                        rs.getString("IDCoach"),
                        rs.getString("authorSend"),
                        rs.getDate("feedbackDate"),
                        rs.getString("content")
                );
                list.add(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Gửi tin nhắn mới
    public void insertMessage(Support msg) {
        String sql = "INSERT INTO Support (IDMember, IDCoach, authorSend, feedbackDate, content) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, msg.getIdMember());
            ps.setString(2, msg.getIdCoach());
            ps.setString(3, msg.getAuthorSend());
            ps.setDate(4, msg.getFeedbackDate());
            ps.setString(5, msg.getContent());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy IDCoach từ IDMember
    public String getCoachIdByMember(String idMember) {
        String idCoach = null;
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT IDCoach FROM Member WHERE IDMember = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idMember);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idCoach = rs.getString("IDCoach");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idCoach;
    }

    public List<Support> getAllMessagesBetween(String idMember, String idCoach) {
        List<Support> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM Support WHERE IDMember = ? AND IDCoach = ? ORDER BY feedbackDate ASC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idMember);
            ps.setString(2, idCoach);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Support s = new Support();
                s.setIdSupport(rs.getInt("IDSupport"));
                s.setIdMember(rs.getString("IDMember"));
                s.setIdCoach(rs.getString("IDCoach"));
                s.setAuthorSend(rs.getString("authorSend"));
                s.setFeedbackDate(rs.getDate("feedbackDate"));
                s.setContent(rs.getString("content"));
                list.add(s);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertSupport(Support s) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO Support(IDMember, IDCoach, authorSend, feedbackDate, content) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getIdMember());
            ps.setString(2, s.getIdCoach());
            ps.setString(3, s.getAuthorSend());
            ps.setDate(4, s.getFeedbackDate());
            ps.setString(5, s.getContent());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Member> getMembersOfCoach(String idCoach) {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM Member WHERE IDCoach = ?";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idCoach);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Member m = new Member();
                m.setIDMember(rs.getString("IDMember"));
                m.setMemberName(rs.getString("memberName"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    public List<Support> getChatBetween(String memberId, String coachId) {
        List<Support> chat = new ArrayList<>();
        String sql = "SELECT * FROM Support WHERE IDMember = ? AND IDCoach = ? ORDER BY feedbackDate ASC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            ps.setString(2, coachId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Support s = new Support();
                    s.setIdSupport(rs.getInt("IDSupport"));
                    s.setIdMember(rs.getString("IDMember"));
                    s.setIdCoach(rs.getString("IDCoach"));
                    s.setAuthorSend(rs.getString("authorSend"));
                    s.setFeedbackDate(rs.getDate("feedbackDate"));
                    s.setContent(rs.getString("content"));
                    chat.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chat;
    }
}
