/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import Utils.DBUtils;

public class BadgeDetailDAO {

    // Đếm tiến trình đã submit
    public int countSubmittedProgress(String idMember) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM ProgressLog WHERE IDMember = ? AND status = 'submit'";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idMember);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    // Kiểm tra member đã có huy hiệu chưa
    public boolean hasBadge(String idMember, String idBadge) throws SQLException, ClassNotFoundException {
        String sql = "SELECT 1 FROM BadgeDetail WHERE IDMember = ? AND IDBadge = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idMember);
            stmt.setString(2, idBadge);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    // Insert huy hiệu
    public void insertBadge(String idMember, String idBadge) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO BadgeDetail(IDMember, IDBadge, badgeDate) VALUES (?, ?, GETDATE())";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idMember);
            stmt.setString(2, idBadge);
            stmt.executeUpdate();
        }
    }

    // Đếm số tiến trình có điểm >= threshold
    public int countProgressWithPoint(String idMember, int threshold) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM ProgressLog WHERE IDMember = ? AND status = 'submit' AND point >= ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idMember);
            stmt.setInt(2, threshold);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    // Gán tất cả huy hiệu nếu đủ điều kiện
    public void checkAndGrantBadges(String idMember) throws SQLException, ClassNotFoundException {
        int count = countSubmittedProgress(idMember);

        if (count >= 3 && !hasBadge(idMember, "BG01")) insertBadge(idMember, "BG01");
        if (count >= 5 && !hasBadge(idMember, "BG02")) insertBadge(idMember, "BG02");
        if (count >= 7 && !hasBadge(idMember, "BG03")) insertBadge(idMember, "BG03");
        if (count >= 10 && !hasBadge(idMember, "BG04")) insertBadge(idMember, "BG04");
        if (count >= 15 && !hasBadge(idMember, "BG05")) insertBadge(idMember, "BG05");

        // Theo điểm
        int count70 = countProgressWithPoint(idMember, 70);
        if (count70 >= 1 && !hasBadge(idMember, "BG06")) insertBadge(idMember, "BG06");

        int count90 = countProgressWithPoint(idMember, 90);
        if (count90 >= 1 && !hasBadge(idMember, "BG07")) insertBadge(idMember, "BG07");
    }
}
