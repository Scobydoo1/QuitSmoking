/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BlogPost;
import DTO.Payment;
import DTO.QuitPlan;
import DTO.RegistrationPayment;
import static Utils.DBUtils.getConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Registration;

/**
 *
 * @author Nghia
 */
public class SystemDao {

    public List<QuitPlan> getAllQuitPlans() throws ClassNotFoundException {
        List<QuitPlan> list = new ArrayList<>();
        String query = "SELECT IDQuitPlan, periodOfTime, goals, progress, price FROM QuitPlan";

        try {

            PreparedStatement ps = getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                QuitPlan plan = new QuitPlan();
                plan.setIdQuitPlan(rs.getString("IDQuitPlan"));
                plan.setPeriodOfTime(rs.getInt("periodOfTime"));
                plan.setGoals(rs.getString("goals"));
                plan.setProgress(rs.getString("progress"));
                plan.setPrice(rs.getDouble("price"));

                list.add(plan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<BlogPost> searchByTitle(String keyword) throws ClassNotFoundException {
        List<BlogPost> list = new ArrayList<>();
        String sql = "SELECT * FROM BlogPost WHERE title LIKE ?";

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String id = rs.getString("IDPost");
                String idmem = rs.getString("IDMember");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                Date date = rs.getDate("publishDate");
                BlogPost post = new BlogPost(id, idmem, title, content, image, date);
                list.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public BlogPost getBlogById(String idPost) throws SQLException, ClassNotFoundException {
        String sql = "SELECT IDPost, IDMember, title, content, image, publishDate "
                + "FROM BlogPost WHERE IDPost = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, idPost);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new BlogPost(
                            rs.getString("IDPost"),
                            rs.getString("IDMember"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("image"),
                            rs.getDate("publishDate")
                    );
                }
            }
        }
        return null;
    }

    public List<Payment> getAllPayments() throws ClassNotFoundException {
        List<Payment> payments = new ArrayList<>();

        String sql = "SELECT * FROM Payment"; // Câu lệnh SQL để lấy tất cả các phương thức thanh toán

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            // Duyệt qua kết quả và thêm vào list
            while (rs.next()) {
                String idPayment = rs.getString("IDPayment");
                String method = rs.getString("method");
                String logo = rs.getString("logo");  // Lấy logo từ cơ sở dữ liệu
                Payment payment = new Payment(idPayment, method, logo);
                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    public boolean insertQuitPlanRegistration(String idMember, String idPayment, String idQuitPlan, String status) throws ClassNotFoundException {
        String sql = "INSERT INTO QuitPlanRegistration \n"
                + "                    (IDMember, IDPayment, IDQuitPlan, status,registerDate) \n"
                + "                   VALUES (?, ?, ?, ?, ? )";

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            // Thiết lập các tham số cho PreparedStatement
            ps.setString(1, idMember);
            ps.setString(2, idPayment);
            ps.setString(3, idQuitPlan);
            ps.setString(4, status);
            java.util.Date now = new java.util.Date();
            ps.setDate(5, new java.sql.Date(now.getTime()));

            // Thực thi câu lệnh SQL
            int result = ps.executeUpdate();
            return result > 0; // Trả về true nếu số dòng bị ảnh hưởng > 0
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi thì trả về false
        }
    }

    public int updatePost(BlogPost post) {
        String sql = "UPDATE BlogPost "
                + "SET title       = ?, "
                + "    content     = ?, "
                + "    image       = ?, "
                + "    publishDate = ? "
                + "WHERE IDPost    = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getImage());
            java.util.Date now = new java.util.Date();
            ps.setDate(4, new java.sql.Date(now.getTime()));
            ps.setString(5, post.getIdPost());

            int rows = ps.executeUpdate();
            return rows;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return 0;
            // Tuỳ nhu cầu bạn có thể ném unchecked exception hoặc log tiếp
        }
    }

    public boolean deletePost(String idPost) throws ClassNotFoundException {
        String sql = "DELETE FROM BlogPost WHERE IDPost = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, idPost);
            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<RegistrationPayment> getAllRegistrations() throws SQLException, ClassNotFoundException {
        List<RegistrationPayment> registrations = new ArrayList<>();
        String sql = "SELECT * FROM QuitPlanRegistration";  // Câu lệnh SQL để lấy tất cả bản ghi

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            // Duyệt qua kết quả và thêm vào list
            while (rs.next()) {
                int idRegistration = rs.getInt("IDRegistration");
                String idMember = rs.getString("IDMember");
                String idPayment = rs.getString("IDPayment");
                String idQuitPlan = rs.getString("IDQuitPlan");
                String status = rs.getString("status");
                Date registerDate = rs.getDate("registerDate");

                // Tạo đối tượng Registration và thêm vào danh sách
                RegistrationPayment registration = new RegistrationPayment(idRegistration, idMember, idPayment, idQuitPlan, status, registerDate);
                registrations.add(registration);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return registrations;  // Trả về danh sách các bản ghi
    }

    public RegistrationPayment getByMember(String memberId) {
        RegistrationPayment registrationPayment = null;
        String sql = "SELECT top 1 IDRegistration, IDMember, IDPayment, IDQuitPlan, status, registerDate\n" +
"FROM QuitPlanRegistration\n" +
"WHERE IDMember = ?\n" +
"ORDER BY registerDate DESC"; // Sắp xếp theo registerDate giảm dần và lấy 1 bản ghi đầu tiên

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, memberId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    registrationPayment = new RegistrationPayment(
                            rs.getInt("IDRegistration"),
                            rs.getString("IDMember"),
                            rs.getString("IDPayment"),
                            rs.getString("IDQuitPlan"),
                            rs.getString("status"),
                            rs.getDate("registerDate") // java.sql.Date thừa kế java.util.Date
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return registrationPayment;
    }

    public static void main(String[] args) {
        // Test with a member ID
        String memberId = "CaoVan0982"; // Sử dụng một IDMember hợp lệ từ database của bạn
        SystemDao dao = new SystemDao();

        // Gọi phương thức getByMember
        RegistrationPayment registrationPayment = dao.getByMember(memberId);

        // Kiểm tra kết quả và in ra
        if (registrationPayment != null) {
            System.out.println("IDRegistration: " + registrationPayment.getIDQuitPlan());
            System.out.println("IDMember: " + registrationPayment.getIDMember());
            System.out.println("IDPayment: " + registrationPayment.getIDPayment());
            System.out.println("IDQuitPlan: " + registrationPayment.getIDRegistration());
            System.out.println("Status: " + registrationPayment.getStatus());
            System.out.println("Register Date: " + registrationPayment.getRegisterDate());
        }
    }

}
