    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Notification;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO cho Notification Healthcare Center - Tương thích NetBeans 8.3 & JDK 1.8
 *
 * @author Nguyen Tien Dat
 */
public class NotificationDao {

    public static List<Notification> getNotificationsForMember(String memberId) {
        List<Notification> list = new ArrayList<>();
        String sql = "SELECT n.IDNotification, n.type, n.message, mn.date "
                + "FROM Notification n "
                + "JOIN MemberNotification mn ON n.IDNotification = mn.IDNotification "
                + "WHERE mn.IDMember = ? "
                + "ORDER BY mn.date DESC";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notification n = new Notification();
                n.setIdNotification(rs.getString("IDNotification"));
                n.setType(rs.getString("type"));
                n.setMessage(rs.getString("message"));
                n.setDate(rs.getDate("date"));
                list.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Lấy tất cả notifications của member
    public static List getNotificationsByUserId(String memberId) {
        List list = new ArrayList();
        String sql = "SELECT n.IDNotification, n.type, n.message, mn.date, mn.isRead "
                + "FROM Notification n "
                + "JOIN MemberNotification mn ON n.IDNotification = mn.IDNotification "
                + "WHERE mn.IDMember = ? "
                + "ORDER BY mn.date DESC";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notification n = new Notification();
                n.setIdNotification(rs.getString("IDNotification"));
                n.setType(rs.getString("type"));
                n.setMessage(rs.getString("message"));
                n.setDate(rs.getDate("date"));
                n.setRead(rs.getBoolean("isRead"));
                list.add(n);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Lấy số lượng notifications chưa đọc (cho notification badge)
    public static int getUnreadCount(String memberId) {
        String sql = "SELECT COUNT(*) FROM MemberNotification WHERE IDMember = ? AND isRead = 0";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                rs.close();
                ps.close();
                conn.close();
                return count;
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Lấy notifications gần nhất (cho sidebar dropdown)
    public static List getRecentNotifications(String memberId, int limit) {
        List list = new ArrayList();
        String sql = "SELECT TOP " + limit + " n.IDNotification, n.type, n.message, mn.date, mn.isRead "
                + "FROM Notification n "
                + "JOIN MemberNotification mn ON n.IDNotification = mn.IDNotification "
                + "WHERE mn.IDMember = ? "
                + "ORDER BY mn.date DESC";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notification n = new Notification();
                n.setIdNotification(rs.getString("IDNotification"));
                n.setType(rs.getString("type"));
                n.setMessage(rs.getString("message"));
                n.setDate(rs.getDate("date"));
                n.setRead(rs.getBoolean("isRead"));
                list.add(n);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Lấy notifications chưa đọc
    public static List getUnreadNotifications(String memberId) {
        List list = new ArrayList();
        String sql = "SELECT n.IDNotification, n.type, n.message, mn.date, mn.isRead "
                + "FROM Notification n "
                + "JOIN MemberNotification mn ON n.IDNotification = mn.IDNotification "
                + "WHERE mn.IDMember = ? AND mn.isRead = 0 "
                + "ORDER BY mn.date DESC";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notification n = new Notification();
                n.setIdNotification(rs.getString("IDNotification"));
                n.setType(rs.getString("type"));
                n.setMessage(rs.getString("message"));
                n.setDate(rs.getDate("date"));
                n.setRead(rs.getBoolean("isRead"));
                list.add(n);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Đánh dấu tất cả notifications đã đọc
    public static boolean markAllAsRead(String memberId) {
        String sql = "UPDATE MemberNotification SET isRead = 1 WHERE IDMember = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, memberId);
            int result = ps.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Đánh dấu một notification đã đọc
    public static boolean markAsRead(String notificationId, String memberId) {
        String sql = "UPDATE MemberNotification SET isRead = 1 WHERE IDNotification = ? AND IDMember = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, notificationId);
            ps.setString(2, memberId);

            int result = ps.executeUpdate();
            ps.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa notification của member
    public static boolean deleteNotification(String notificationId, String memberId) {
        String sql = "DELETE FROM MemberNotification WHERE IDNotification = ? AND IDMember = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, notificationId);
            ps.setString(2, memberId);

            int result = ps.executeUpdate();
            ps.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Thêm notification mới
    public static boolean addNotification(String idNotification, String type, String message) {
        String sql = "INSERT INTO Notification (IDNotification, type, message) VALUES (?, ?, ?)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idNotification);
            ps.setString(2, type);
            ps.setString(3, message);

            int result = ps.executeUpdate();
            ps.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Gửi notification cho member
    public static boolean sendNotificationToMember(String idNotification, String memberId) {
        String sql = "INSERT INTO MemberNotification (IDNotification, IDMember, date, isRead) VALUES (?, ?, GETDATE(), 0)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idNotification);
            ps.setString(2, memberId);

            int result = ps.executeUpdate();
            ps.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tạo và gửi notification trong một transaction
    public static boolean createAndSendNotification(String type, String message, String memberId) {
        String notificationId = "NOTIF" + System.currentTimeMillis();

        try {
            Connection conn = DBUtils.getConnection();
            conn.setAutoCommit(false);

            // Tạo notification
            String sqlNotif = "INSERT INTO Notification (IDNotification, type, message) VALUES (?, ?, ?)";
            PreparedStatement ps1 = conn.prepareStatement(sqlNotif);
            ps1.setString(1, notificationId);
            ps1.setString(2, type);
            ps1.setString(3, message);
            ps1.executeUpdate();
            ps1.close();

            // Gửi cho member
            String sqlMember = "INSERT INTO MemberNotification (IDNotification, IDMember, date, isRead) VALUES (?, ?, GETDATE(), 0)";
            PreparedStatement ps2 = conn.prepareStatement(sqlMember);
            ps2.setString(1, notificationId);
            ps2.setString(2, memberId);
            ps2.executeUpdate();
            ps2.close();

            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Gửi notification cho nhiều members
    public static boolean createAndSendNotificationToMembers(String type, String message, List memberIds) {
        String notificationId = "NOTIF" + System.currentTimeMillis();

        try {
            Connection conn = DBUtils.getConnection();
            conn.setAutoCommit(false);

            // Tạo notification
            String sqlNotif = "INSERT INTO Notification (IDNotification, type, message) VALUES (?, ?, ?)";
            PreparedStatement ps1 = conn.prepareStatement(sqlNotif);
            ps1.setString(1, notificationId);
            ps1.setString(2, type);
            ps1.setString(3, message);
            ps1.executeUpdate();
            ps1.close();

            // Gửi cho các members
            String sqlMember = "INSERT INTO MemberNotification (IDNotification, IDMember, date, isRead) VALUES (?, ?, GETDATE(), 0)";
            PreparedStatement ps2 = conn.prepareStatement(sqlMember);

            for (int i = 0; i < memberIds.size(); i++) {
                String memberId = (String) memberIds.get(i);
                ps2.setString(1, notificationId);
                ps2.setString(2, memberId);
                ps2.addBatch();
            }

            ps2.executeBatch();
            ps2.close();

            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy notifications theo loại
    public static List getNotificationsByType(String memberId, String type) {
        List list = new ArrayList();
        String sql = "SELECT n.IDNotification, n.type, n.message, mn.date, mn.isRead "
                + "FROM Notification n "
                + "JOIN MemberNotification mn ON n.IDNotification = mn.IDNotification "
                + "WHERE mn.IDMember = ? AND n.type = ? "
                + "ORDER BY mn.date DESC";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberId);
            ps.setString(2, type);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notification n = new Notification();
                n.setIdNotification(rs.getString("IDNotification"));
                n.setType(rs.getString("type"));
                n.setMessage(rs.getString("message"));
                n.setDate(rs.getDate("date"));
                n.setRead(rs.getBoolean("isRead"));
                list.add(n);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Kiểm tra notification có tồn tại không
    public static boolean notificationExists(String notificationId, String memberId) {
        String sql = "SELECT COUNT(*) FROM MemberNotification WHERE IDNotification = ? AND IDMember = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, notificationId);
            ps.setString(2, memberId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                rs.close();
                ps.close();
                conn.close();
                return count > 0;
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Xóa notifications cũ (quá 30 ngày)
    public static boolean deleteOldNotifications() {
        String sql = "DELETE FROM MemberNotification WHERE date < DATEADD(day, -30, GETDATE())";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int result = ps.executeUpdate();
            ps.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    // Helper methods để tạo notifications nhanh cho healthcare
//    public static class HealthcareNotifications {
//
//        public static boolean sendAppointmentReminder(String memberId, String appointmentDate) {
//            String message = "Nhắc nhở: Bạn có lịch hẹn khám bệnh vào ngày " + appointmentDate + ". Vui lòng đến đúng giờ.";
//            return createAndSendNotification("reminder", message, memberId);
//        }
//
//        public static boolean sendMedicationReminder(String memberId, String medicationName) {
//            String message = "Đã đến giờ uống thuốc " + medicationName + ". Hãy tuân thủ đúng liều lượng theo chỉ định của bác sĩ.";
//            return createAndSendNotification("reminder", message, memberId);
//        }
//
//        public static boolean sendQuitPlanSuccess(String memberId, String milestone) {
//            String message = "Chúc mừng! Bạn đã đạt được " + milestone + " trong chương trình cai thuốc. Hãy tiếp tục cố gắng!";
//            return createAndSendNotification("success", message, memberId);
//        }
//
//        public static boolean sendHealthWarning(String memberId, String warningMessage) {
//            String message = "Cảnh báo sức khỏe: " + warningMessage + ". Vui lòng liên hệ bác sĩ nếu cần thiết.";
//            return createAndSendNotification("warning", message, memberId);
//        }
//
//        public static boolean sendGeneralInfo(String memberId, String infoMessage) {
//            String message = "Thông tin: " + infoMessage;
//            return createAndSendNotification("info", message, memberId);
//        }
//
//        public static boolean sendWelcomeMessage(String memberId, String memberName) {
//            String message = "Chào mừng " + memberName + " đến với hệ thống chăm sóc sức khỏe BFBB. Chúng tôi cam kết mang đến dịch vụ y tế chất lượng cao nhất.";
//            return createAndSendNotification("info", message, memberId);
//        }
//
//        public static boolean sendTestResultNotification(String memberId, String testType) {
//            String message = "Kết quả xét nghiệm " + testType + " của bạn đã có. Vui lòng đăng nhập để xem chi tiết hoặc liên hệ bác sĩ.";
//            return createAndSendNotification("info", message, memberId);
//        }
//
//        public static boolean sendSystemMaintenance(List memberIds) {
//            String message = "Thông báo: Hệ thống sẽ bảo trì từ 2:00 - 4:00 sáng ngày mai. Vui lòng sắp xếp thời gian phù hợp.";
//            return createAndSendNotificationToMembers("warning", message, memberIds);
//        }
//    }
    public static boolean sendNotificationToCoach(String idNotification, String coachId) {
        String sql = "INSERT INTO CoachNotification (IDNotification, IDCoach, date, isRead) VALUES (?, ?, GETDATE(), 0)";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idNotification);
            ps.setString(2, coachId);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public static List<Notification> getNotificationsByCoachId(String coachId) throws ClassNotFoundException {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT n.IDNotification, n.type, n.message, c.date, c.isRead " +
                     "FROM CoachNotification c " +
                     "JOIN Notification n ON c.IDNotification = n.IDNotification " +
                     "WHERE c.IDCoach = ?";
        
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, coachId); // Set the coachId parameter

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String idNotification = rs.getString("IDNotification");
                String type = rs.getString("type");
                String message = rs.getString("message");
                Date date = rs.getDate("date");
                boolean isRead = rs.getBoolean("isRead");

                // Create a new Notification object and add it to the list
                Notification notification = new Notification(idNotification, type, message, date, isRead);
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }
    
       
        
    
}
