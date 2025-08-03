/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Badge;
import DTO.Member;
import DTO.MemberBadgeRankingDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Tien Dat
 */
public class BadgeDAO {

    public List<Badge> getBadgesByMember(String idMember) throws Exception {
        List<Badge> list = new ArrayList<>();
        String sql = "SELECT b.IDBadge, b.name, b.condition, b.description "
                + "FROM Badge b JOIN BadgeDetail bd ON b.IDBadge = bd.IDBadge "
                + "WHERE bd.IDMember = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idMember);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Badge badge = new Badge(
                        rs.getString("IDBadge"),
                        rs.getString("name"),
                        rs.getString("condition"),
                        rs.getString("description")
                );
                list.add(badge);
            }
        }
        return list;
    }

//    public List<MemberBadgeRankingDTO> getRankingByStatus() throws Exception {
//        List<MemberBadgeRankingDTO> list = new ArrayList<>();
//        String sql
//                = "SELECT m.IDMember, m.memberName, m.status, COUNT(bd.IDBadge) AS badgeCount "
//                + "FROM Member m "
//                + "JOIN BadgeDetail bd ON m.IDMember = bd.IDMember "
//                + "GROUP BY m.IDMember, m.memberName, m.status "
//                + "ORDER BY m.status, badgeCount DESC";
//
//        try (Connection conn = DBUtils.getConnection();
//                PreparedStatement stmt = conn.prepareStatement(sql);
//                ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                MemberBadgeRankingDTO rank = new MemberBadgeRankingDTO();
//                rank.setIdMember(rs.getString("IDMember"));
//                rank.setMemberName(rs.getString("memberName"));
//                rank.setStatus(rs.getString("status"));
//                rank.setBadgeCount(rs.getInt("badgeCount"));
//                list.add(rank);
//            }
//        }
//        return list;
//    }
    public static List<Member> getProgressRankingByPayment(String idPayment) throws Exception {
        List<Member> list = new ArrayList<>();

        String sql = "SELECT m.IDMember, m.memberName, "
                + "SUM(CASE "
                + "    WHEN p.type = '3 day' THEN ISNULL(p.point, 0) * 0.2 "
                + "    WHEN p.type = '5 day' THEN ISNULL(p.point, 0) * 0.3 "
                + "    WHEN p.type = '7 day' THEN ISNULL(p.point, 0) * 0.5 "
                + "    ELSE 0 END) AS totalPoint "
                + "FROM Member m "
                + "JOIN QuitPlanRegistration qpr ON m.IDMember = qpr.IDMember "
                + "LEFT JOIN ProgressLog p ON m.IDMember = p.IDMember "
                + "WHERE qpr.IDQuitPlan = ? "
                + "GROUP BY m.IDMember, m.memberName "
                + "HAVING SUM(CASE "
                + "    WHEN p.type = '3 day' THEN ISNULL(p.point, 0) * 0.2 "
                + "    WHEN p.type = '5 day' THEN ISNULL(p.point, 0) * 0.3 "
                + "    WHEN p.type = '7 day' THEN ISNULL(p.point, 0) * 0.5 "
                + "    ELSE 0 END) > 0 "
                + "ORDER BY totalPoint DESC";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idPayment); // ví dụ: QP01 = Silver

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Member m = new Member();
                m.setIDMember(rs.getString("IDMember"));
                m.setMemberName(rs.getString("memberName"));
                m.setPoint((int) rs.getDouble("totalPoint")); // ép double sang int
                list.add(m);
            }
        }

        return list;
    }

    public int getMemberCount(String idMember) throws SQLException, ClassNotFoundException {
        String sql = "  SELECT \n"
                + "                COUNT(*) AS MemberCount\n"
                + "            FROM dbo.ProgressLog\n"
                + "            WHERE IDMember = ?\n"
                + "            GROUP BY IDMember";

        int memberCount = 0;

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idMember);  // Gán giá trị idMember vào tham số SQL
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    memberCount = rs.getInt("MemberCount");
                }
            }
        }

        return memberCount;
    }

    public String getBadgeByCount(int memberCount) {
        // Dùng else if để kiểm tra giá trị gần nhất
        if (memberCount >= 180) {
            return "BG11";
        } else if (memberCount >= 150) {
            return "BG10";
        } else if (memberCount >= 120) {
            return "BG09";
        } else if (memberCount >= 90) {
            return "BG08";
        } else if (memberCount >= 60) {
            return "BG07";
        } else if (memberCount >= 45) {
            return "BG06";
        } else if (memberCount >= 30) {
            return "BG05";
        } else if (memberCount >= 15) {
            return "BG04";
        } else if (memberCount >= 10) {
            return "BG03";
        } else if (memberCount >= 5) {
            return "BG02";
        } else if (memberCount >= 3) {
            return "BG01";
        } else {
            return "";  // Trả về chuỗi rỗng nếu không có match
        }
    }

    public boolean insertBadgeDetail(String idMember) throws SQLException, ClassNotFoundException {
        // Câu lệnh SQL được đặt trong dấu ngoặc luôn
        String sql = "INSERT INTO dbo.BadgeDetail (IDMember, IDBadge, badgeDate) VALUES (?, ?, ?)";
        int memberCount = getMemberCount(idMember);  // Lấy số lượng
        String idBadge = getBadgeByCount(memberCount);  // Lấy IDBadge theo count

        // Nếu không có IDBadge hợp lệ, trả về false
        if (idBadge.isEmpty()) {
            return false;
        }
        // Sử dụng PreparedStatement để tránh SQL Injection
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idMember);  // Gán IDMember
            stmt.setString(2, idBadge);   // Gán IDBadge

            // Lấy ngày hiện tại
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            stmt.setDate(3, currentDate);  // Gán ngày hiện tại vào badgeDate

            // Thực thi câu lệnh INSERT
            int rowsAffected = stmt.executeUpdate();

            // Kiểm tra nếu có ít nhất một dòng được thêm vào
            return rowsAffected > 0;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BadgeDAO dao = new BadgeDAO();
        dao.insertBadgeDetail("07");
    }
}
