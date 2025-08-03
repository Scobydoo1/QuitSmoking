/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.QuitPlan;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nguyen Tien Dat
 */
public class QuitPlanDAO {

    public static List<QuitPlan> getAllPlans() {
        List<QuitPlan> list = new ArrayList<>();
        String sql = "SELECT * FROM QuitPlan";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                QuitPlan plan = new QuitPlan();
                plan.setIdQuitPlan(rs.getString("IDQuitPlan"));
                plan.setPeriodOfTime(rs.getInt("periodOfTime"));
                plan.setGoals(rs.getString("goals"));
                plan.setProgress(rs.getString("progress"));
                plan.setPrice(rs.getInt("price"));
                list.add(plan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static QuitPlan getPlanById(String id) {
        String sql = "SELECT * FROM QuitPlan WHERE IDQuitPlan = ?";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    QuitPlan plan = new QuitPlan();
                    plan.setIdQuitPlan(rs.getString("IDQuitPlan"));
                    plan.setPeriodOfTime(rs.getInt("periodOfTime"));
                    plan.setGoals(rs.getString("goals"));
                    plan.setProgress(rs.getString("progress"));
                    plan.setPrice(rs.getInt("price"));
                    return plan;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<QuitPlan> getAllQuitPlans() {
        String sql = "SELECT IDQuitPlan, periodOfTime, goals, progress, price, status FROM dbo.QuitPlan";
        List<QuitPlan> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                QuitPlan qp = new QuitPlan();
                qp.setIdQuitPlan(rs.getString("IDQuitPlan"));
                qp.setPeriodOfTime(rs.getInt("periodOfTime"));
                qp.setGoals(rs.getString("goals"));
                qp.setProgress(rs.getString("progress"));
                qp.setPrice(rs.getDouble("price"));
                qp.setStatus(rs.getString("status"));  // <-- thêm dòng này
                list.add(qp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteQuitPlan(String idQuitPlan) {
        String sql = "UPDATE dbo.QuitPlan SET status = ? WHERE IDQuitPlan = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            // 1: dùng tham số để đảm bảo an toàn, dễ thay đổi sau này
            ps.setString(1, "2");
            ps.setString(2, idQuitPlan);

            int rows = ps.executeUpdate();
            System.out.println("[DAO] deleteQuitPlan → rowsAffected = " + rows);
            return rows > 0;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateQuitPlan(QuitPlan qp) {
        String sql = "UPDATE dbo.QuitPlan "
                + "SET periodOfTime = ?, "
                + "    goals        = ?, "
                + "    progress     = ?, "
                + "    price        = ? "
                + "WHERE IDQuitPlan = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, qp.getPeriodOfTime());
            ps.setString(2, qp.getGoals());
            ps.setString(3, qp.getProgress());
            ps.setDouble(4, qp.getPrice());
            ps.setString(5, qp.getIdQuitPlan());

            int rows = ps.executeUpdate();
            System.out.println("[DAO] updateQuitPlan → rowsAffected = " + rows);
            return rows > 0;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertQuitPlan(QuitPlan qp) {
        String sql = "INSERT INTO dbo.QuitPlan "
                + "(IDQuitPlan, periodOfTime, goals, progress, price) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, qp.getIdQuitPlan());
            ps.setInt(2, qp.getPeriodOfTime());
            ps.setString(3, qp.getGoals());
            ps.setString(4, qp.getProgress());
            ps.setDouble(5, qp.getPrice());

            int rows = ps.executeUpdate();
            System.out.println("[DAO] insertQuitPlan → rowsAffected = " + rows);
            return rows > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getRegisteredMemberCount() throws SQLException, ClassNotFoundException {
        int count = 0;
        String query = "SELECT COUNT(DISTINCT IDMember) FROM QuitPlanRegistration";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    
    public Map<String, Integer> getMemberCountByQuitPlan() throws Exception {
        Map<String, Integer> result = new HashMap<>();

        String query = "SELECT IDQuitPlan, COUNT(DISTINCT IDMember) AS memberCount " +
                       "FROM QuitPlanRegistration " +
                       "GROUP BY IDQuitPlan";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String planId = rs.getString("IDQuitPlan");
                int count = rs.getInt("memberCount");
                result.put(planId, count);
            }
        }

        return result;
    }
}
