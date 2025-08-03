/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Utils.DBUtils.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import DTO.Coach;
import DTO.Member;
import Utils.DBUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen
 */
public class CoachDao {

    public Coach checkLogin(String username, String pass) throws ClassNotFoundException {
        String sql = "SELECT * FROM Coach WHERE IDCoach = ? AND password = ?";
        Connection con = null;
        Coach coach = null;

        try {
            // Mở kết nối đến database
            con = getConnection();

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, pass);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                coach = new Coach();

                coach.setIDCoach(rs.getString("IDCoach"));
                coach.setPassword(rs.getString("password"));
                coach.setCoachName(rs.getString("coachName"));
                coach.setGender(rs.getString("gender")); // mới thêm
                coach.setPhone(rs.getString("phone"));
                coach.setEmail(rs.getString("email"));
                coach.setAddress(rs.getString("address"));
                coach.setImage(rs.getString("image")); // mới thêm
                coach.setDateOfBirth(rs.getDate("dateOfBirth"));
                coach.setSpecialization(rs.getString("specialization"));
                coach.setExperienceYears(rs.getInt("experienceYears"));
                coach.setStatus(rs.getString("status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return coach;
    }

    public ArrayList<Coach> getAllCoaches() throws Exception {
        ArrayList<Coach> list = new ArrayList<>();
        String sql = "SELECT * FROM Coach";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Coach coach = new Coach();
                coach.setIDCoach(rs.getString("IDCoach"));
                coach.setPassword(rs.getString("password"));
                coach.setCoachName(rs.getString("coachName"));
                coach.setGender(rs.getString("gender"));
                coach.setPhone(rs.getString("phone"));
                coach.setEmail(rs.getString("email"));
                coach.setAddress(rs.getString("address"));
                coach.setImage(rs.getString("image"));
                coach.setDateOfBirth(rs.getDate("dateOfBirth"));
                coach.setSpecialization(rs.getString("specialization"));
                coach.setExperienceYears(rs.getInt("experienceYears"));
                coach.setStatus(rs.getString("status"));
                list.add(coach);
            }
        }
        return list;
    }

    public void updateStatus(String coachID, String status) throws Exception {
        String sql = "UPDATE Coach SET status = ? WHERE IDCoach = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, coachID);
            ps.executeUpdate();
        }
    }

    public List<Member> getMembersForCoach(String coachId) throws SQLException, ClassNotFoundException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM Member WHERE IDCoach = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, coachId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Member m = new Member();
                m.setIDMember(rs.getString("IDMember"));
                m.setMemberName(rs.getString("memberName"));
                m.setGender(rs.getString("gender"));
                m.setPhone(rs.getString("phone"));
                m.setEmail(rs.getString("email"));
                m.setAddress(rs.getString("address"));
                m.setDateOfBirth(rs.getDate("dateOfBirth"));
                m.setJoinDate(rs.getDate("joinDate"));
                m.setImage(rs.getString("image"));
                m.setPoint(rs.getInt("point"));
                m.setIDCoach(rs.getString("IDCoach"));
                m.setSubscription(rs.getString("subcription"));
                m.setStatus(rs.getString("status"));
                members.add(m);
            }
        }
        return members;
    }

    public String getCoachIdFromMember(String idMem) throws ClassNotFoundException {
        String sql = "SELECT c.IDCoach\n"
                + "FROM Member m\n"
                + "JOIN Coach c ON m.IDCoach = c.IDCoach\n"
                + "WHERE m.IDMember = ?;";

        String coachName = null;

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idMem);  // Thiết lập giá trị IDMember vào câu lệnh SQL
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                coachName = rs.getString("IDCoach");  // Lấy tên huấn luyện viên từ kết quả truy vấn
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coachName;
    }

    public Coach getCoachById(String idCoach) throws ClassNotFoundException {
        Coach coach = null;
        String query = "SELECT * FROM Coach WHERE IDCoach = ?";

        try {

            PreparedStatement st = getConnection().prepareStatement(query);
            st.setString(1, idCoach);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                coach = new Coach();
                coach.setIDCoach(rs.getString("IDCoach"));
                coach.setPassword(rs.getString("password"));
                coach.setCoachName(rs.getString("coachName"));
                coach.setGender(rs.getString("gender"));
                coach.setPhone(rs.getString("phone"));
                coach.setEmail(rs.getString("email"));
                coach.setAddress(rs.getString("address"));
                coach.setImage(rs.getString("image"));
                coach.setDateOfBirth(rs.getDate("dateOfBirth"));
                coach.setSpecialization(rs.getString("specialization"));
                coach.setExperienceYears(rs.getInt("experienceYears"));
                coach.setStatus(rs.getString("status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coach;
    }

    public boolean updateCoachProfile(Coach coach) throws ClassNotFoundException {
        String sql = "UPDATE Coach SET password = ?, coachName = ?, gender = ?, phone = ?, email = ?, address = ?, dateOfBirth = ?, specialization = ?, experienceYears = ?, image = ? WHERE IDCoach = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, coach.getPassword());
            ps.setString(2, coach.getCoachName());
            ps.setString(3, coach.getGender());
            ps.setString(4, coach.getPhone());
            ps.setString(5, coach.getEmail());
            ps.setString(6, coach.getAddress());

            // ✅ Xử lý ngày sinh có thể null
            if (coach.getDateOfBirth() != null) {
                ps.setDate(7, coach.getDateOfBirth());
            } else {
                ps.setNull(7, java.sql.Types.DATE);
            }

            ps.setString(8, coach.getSpecialization());

            ps.setInt(9, coach.getExperienceYears());

            if (coach.getImage() != null && !coach.getImage().trim().isEmpty()) {
                ps.setString(10, coach.getImage());
            } else {
                ps.setNull(10, java.sql.Types.VARCHAR);
            }

            ps.setString(11, coach.getIDCoach());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Error updating coach profile: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void insertCoach(Coach coach) throws Exception {
        String sql = "INSERT INTO Coach (IDCoach, password, coachName, gender, phone, email, address, dateOfBirth, specialization, experienceYears, image, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, coach.getIDCoach());
            ps.setString(2, coach.getPassword());
            ps.setString(3, coach.getCoachName());
            ps.setString(4, coach.getGender());
            ps.setString(5, coach.getPhone());
            ps.setString(6, coach.getEmail());
            ps.setString(7, coach.getAddress());

            if (coach.getDateOfBirth() != null) {
                ps.setDate(8, coach.getDateOfBirth());
            } else {
                ps.setNull(8, java.sql.Types.DATE);
            }

            ps.setString(9, coach.getSpecialization());
            ps.setInt(10, coach.getExperienceYears());

            if (coach.getImage() != null && !coach.getImage().trim().isEmpty()) {
                ps.setString(11, coach.getImage());
            } else {
                ps.setNull(11, java.sql.Types.VARCHAR);
            }

            ps.setString(12, coach.getStatus());

            ps.executeUpdate();
        }
    }

    public int getTotalCoachCount() throws SQLException, ClassNotFoundException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Coach";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }
}
