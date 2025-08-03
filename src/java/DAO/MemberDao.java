/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BlogPost;
import static Utils.DBUtils.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DTO.Member;
import Utils.DBUtils;
import java.sql.Date;

/**
 *
 * @author Nguyen Tien Dat
 */
/////
public class MemberDao {

    private Connection conn;

    public MemberDao() {
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Member checkLogin(String username, String pass) throws ClassNotFoundException {
        String sql = "SELECT *\n"
                + "FROM Member\n"
                + "WHERE IDMember = ? AND password = ?;";
        Connection con = null;
        Member member = null;

        try {

            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                String id = rs.getString(1); // IDMember
                String password = rs.getString(2); // password
                String name = rs.getString(3); // memberName
                String gender = rs.getString(4);
                String phone = rs.getString(5); // phone
                String email = rs.getString(6); // email
                String address = rs.getString(7); // address
                Date dob = rs.getDate(8); // dateOfBirth
                Date joinDate = rs.getDate(9); // joinDate
                String avata = rs.getString(10);
                int point = rs.getInt(11);
                String coach = rs.getString(12); // IDCoach
                String subscription = rs.getString(13); // subscription
                String status = rs.getString(14);

                member = new Member(id, password, name, gender, phone, email, address, dob, joinDate, avata, point, coach, subscription, status);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    public void resigter(String id, String password, String memberName, String gender, String phone,
            String email, String address, String dateofBirth, String status) throws ClassNotFoundException {
        String sql = "INSERT INTO Member\n"
                + "(IDMember, password, memberName, gender, phone, email, address, dateOfBirth, joinDate, point , status )\n"
                + "VALUES\n"
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement pstmt = getConnection().prepareStatement(sql);

            pstmt.setString(1, id);
            pstmt.setString(2, password);
            pstmt.setString(3, memberName);
            pstmt.setString(4, gender);
            pstmt.setString(5, phone);
            pstmt.setString(6, email);
            pstmt.setString(7, address);
            pstmt.setString(8, dateofBirth);

            java.util.Date now = new java.util.Date();
            pstmt.setDate(9, new java.sql.Date(now.getTime()));
            pstmt.setInt(10, -1);
            pstmt.setString(11, status);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<String> getAllMemberIds() throws ClassNotFoundException {
        List<String> memberIds = new ArrayList<>();
        String sql = "SELECT IDMember FROM Member";  // Tên bảng chính xác theo DB của bạn

        try {
            PreparedStatement pstmt = getConnection().prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                memberIds.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberIds;
    }

    public List<Member> getMembersByCoach(String idCoach) {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM Member WHERE IDCoach = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, idCoach);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Member m = new Member();
                m.setIDMember(rs.getString("IDMember"));
                m.setMemberName(rs.getString("memberName"));
                // set các trường khác nếu cần
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertBlogPost(String idPost, String idMember, String title, String content, String imagePath, LocalDate publishDate) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO BlogPost (IDPost, IDMember, title, [content], image, publishDate) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);

            stmt.setString(1, idPost);
            stmt.setString(2, idMember);
            stmt.setString(3, title);
            stmt.setString(4, content);
            stmt.setString(5, imagePath); // Đây là chuỗi đường dẫn ảnh
            stmt.setDate(6, java.sql.Date.valueOf(publishDate)); // Convert LocalDate to java.sql.Date

            stmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public List<BlogPost> getAllBlogPosts() throws SQLException, ClassNotFoundException {
        List<BlogPost> blogPosts = new ArrayList<>();

        String sql = "SELECT IDPost, IDMember, title, content, image, publishDate FROM BlogPost";

        // Kết nối cơ sở dữ liệu
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            // Duyệt qua tất cả các bài viết và thêm vào list
            while (rs.next()) {
                String idPost = rs.getString("IDPost");
                String idMember = rs.getString("IDMember");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                Date publishDate = rs.getDate("publishDate");

                // Tạo đối tượng BlogPost và thêm vào danh sách
                BlogPost post = new BlogPost(idPost, idMember, title, content, image, publishDate);
                blogPosts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving blog posts: " + e.getMessage());
        }

        return blogPosts;
    }

    public static boolean updateMember(Member member) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Member SET password=?, memberName=?, gender=?, phone=?, email=?, address=?, dateOfBirth=?, joinDate=?, image=?, IDCoach=?, subcription=?, status=? WHERE IDMember=?";
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, member.getPassword());
            ps.setString(2, member.getMemberName());
            ps.setString(3, member.getGender());
            ps.setString(4, member.getPhone());
            ps.setString(5, member.getEmail());
            ps.setString(6, member.getAddress());

            // dateOfBirth
            // dateOfBirth
            if (member.getDateOfBirth() != null) {
                ps.setDate(7, member.getDateOfBirth());  // đã là java.sql.Date rồi
            } else {
                ps.setNull(7, java.sql.Types.DATE);
            }

// joinDate
            if (member.getJoinDate() != null) {
                ps.setDate(8, member.getJoinDate());  // không cần tạo lại java.sql.Date nữa
            } else {
                ps.setNull(8, java.sql.Types.DATE);
            }

            ps.setString(9, member.getImage());

            ps.setString(10, member.getIDCoach());
            ps.setString(11, member.getSubscription());
            ps.setString(12, member.getStatus());

            ps.setString(13, member.getIDMember());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public Member getMemberById(String id) {
        Member member = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM Member WHERE IDMember = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                member = new Member();
                member.setIDMember(rs.getString("IDMember"));
                member.setPassword(rs.getString("password"));
                member.setMemberName(rs.getString("memberName"));
                member.setGender(rs.getString("gender"));
                member.setPhone(rs.getString("phone"));
                member.setEmail(rs.getString("email"));
                member.setAddress(rs.getString("address"));
                member.setDateOfBirth(rs.getDate("dateOfBirth"));
                member.setJoinDate(rs.getDate("joinDate"));

                member.setImage(rs.getString("image"));

                member.setPoint(rs.getInt("point"));
                member.setIDCoach(rs.getString("IDCoach"));
                member.setSubscription(rs.getString("subcription"));
                member.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }

        return member;
    }

    public List<Member> getMembersForCoach(String coachId) {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM Member WHERE IDCoach = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, coachId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Member member = new Member();
                    member.setIDMember(rs.getString("IDMember"));
                    member.setMemberName(rs.getString("memberName"));
                    member.setGender(rs.getString("gender"));
                    member.setPhone(rs.getString("phone"));
                    member.setEmail(rs.getString("email"));
                    member.setAddress(rs.getString("address"));
                    member.setDateOfBirth(rs.getDate("dateOfBirth"));
                    member.setJoinDate(rs.getDate("joinDate"));
                    member.setImage(rs.getString("image"));
                    member.setPoint(rs.getInt("point"));
                    member.setIDCoach(rs.getString("IDCoach"));
                    member.setSubscription(rs.getString("subcription"));
                    member.setStatus(rs.getString("status"));

                    members.add(member);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return members;
    }

    public static List<Member> getMembersByCoachId(String coachId) {
        List<Member> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "SELECT * FROM Member WHERE IDCoach = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, coachId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Member m = new Member();
                m.setIDMember(rs.getString("IDMember"));
                m.setMemberName(rs.getString("memberName"));
                m.setGender(rs.getString("gender"));
                m.setPhone(rs.getString("phone"));
                m.setEmail(rs.getString("email"));
                m.setPoint(rs.getInt("point"));
                m.setSubscription(rs.getString("subcription"));
                m.setStatus(rs.getString("status"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Kiểm tra nếu có lỗi SQL
        }
        return list;
    }

    public String getCoachIdByMemberId(String idMember) {
        String coachId = null;
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT idCoach FROM Member WHERE idMember = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idMember);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                coachId = rs.getString("idCoach");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coachId;
    }

    public void updatePoint(String idMember, int point) throws Exception {
        String sql = "UPDATE Member SET point = ? WHERE IDMember = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, point);
            ps.setString(2, idMember);
            ps.executeUpdate();
        }
    }

    public String getCoachWithLeastCount() throws ClassNotFoundException {
        String sql = "SELECT top 1 IDCoach, COUNT(IDCoach) AS count\n"
                + "FROM Member\n"
                + "GROUP BY IDCoach\n"
                + "HAVING COUNT(IDCoach) >= 1\n"
                + "ORDER BY count ASC\n"
                + ";";
        String coachId = null;

        try {

            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                coachId = rs.getString("IDCoach");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coachId;
    }

    public boolean updateCoachForMember(String idMem) throws ClassNotFoundException {

        String sql = "UPDATE Member \n"
                + "                    SET IDCoach = ? \n"
                + "                   WHERE IDMember = ?";  // Cập nhật IDCoach cho Member có IDMember = idMem
        String coachId = getCoachWithLeastCount();
        try {

            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, coachId);
            ps.setString(2, idMem);

            int rowsUpdated = ps.executeUpdate();

            return rowsUpdated > 0; // Trả về true nếu có bản ghi được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }

    public boolean updateMemberStatus(String idMember, String status) throws ClassNotFoundException {
        String sql = "UPDATE Member\n"
                + "SET status = ? \n"
                + "WHERE IDMember = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);  // Thiết lập giá trị cho trường status
            ps.setString(2, idMember);  // Thiết lập IDMember cần cập nhật

            // Thực thi câu lệnh UPDATE
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;  // Nếu có bản ghi được cập nhật, trả về true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Trả về false nếu có lỗi
        }
    }

    public List<BlogPost> getPostsByMemberId(String id) {
        List<BlogPost> posts = new ArrayList<>();
        String sql = "SELECT * FROM BlogPost WHERE IDMember = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BlogPost post = new BlogPost();
                post.setIdPost(rs.getString("IDPost"));
                post.setIdMember(rs.getString("IDMember"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setImage(rs.getString("image"));
                post.setPublishDate(rs.getDate("publishDate"));

                posts.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return posts;
    }

    public Member getEmailByMember(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Member dto = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select IDMember, password, memberName, gender, phone, address, dateOfBirth, joinDate, "
                        + "image, point, IDCoach, subcription, status "
                        + "From Member "
                        + "Where email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String IDMember = rs.getString("IDMember");
                    // Assuming you might need the password for the DTO constructor, even if not directly used for token validation
                    String password = rs.getString("password");
                    String memberName = rs.getString("memberName");
                    String gender = rs.getString("gender");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    Date dateOfBirth = rs.getDate("dateOfBirth");
                    Date joinDate = rs.getDate("joinDate");
                    String image = rs.getString("image");
                    int point = rs.getInt("point");
                    String IDCoach = rs.getString("IDCoach");
                    String subcription = rs.getString("subcription");
                    String status = rs.getString("status");
                    dto = new Member(IDMember, password, memberName, gender, phone, email, address, dateOfBirth, joinDate, image, point, IDCoach, subcription, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto; // Return the populated DTO
    }

    public boolean updatePassword(String IDMember, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Update Member "
                        + "Set password = ? "
                        + "Where IDMember = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, IDMember);
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    // this is ADMIN function....
    public List<Member> getAllMembers() {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT IDMember, password, memberName, gender, phone, email, address, "
                + "dateOfBirth, joinDate, image, point, IDCoach, subcription, status "
                + "FROM dbo.Member";  // no WHERE clause, fetch all

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Member m = new Member();
                m.setIDMember(rs.getString("IDMember"));
                m.setPassword(rs.getString("password"));
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

                list.add(m);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Optionally rethrow or log
        }

        return list;
    }

    public boolean deleteMember(String idMember) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE dbo.Member SET status = ? WHERE IDMember = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "2");          // status mới
            ps.setString(2, idMember);     // ID của member

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean restoreMember(String idMember) throws ClassNotFoundException {
        String sql = "UPDATE Member SET status = ? WHERE IDMember = ?";
        try (
                Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "1");
            ps.setString(2, idMember);
            int updated = ps.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Member> searchMembers(String keyword) {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT IDMember, password, memberName, gender, phone, email, address, "
                + "dateOfBirth, joinDate, image, point, IDCoach, subcription, status "
                + "FROM dbo.Member "
                + "WHERE memberName LIKE ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Member m = new Member();
                    m.setIDMember(rs.getString("IDMember"));
                    m.setPassword(rs.getString("password"));
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
                    list.add(m);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalMemberCount() throws SQLException, ClassNotFoundException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Member";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }
    
    public boolean isMemberCompleted(String idMember) {
        String sql = "SELECT COUNT(*) FROM QuitPlanRegistration WHERE IDMember = ? AND status = 'completed'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idMember);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Có ít nhất 1 bản ghi status = completed
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Mặc định là chưa hoàn thành
    }

}
