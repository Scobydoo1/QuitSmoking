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
import DTO.Admin;
import DTO.Member;
import DTO.QuitPlan;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    public Admin login(String IDAdmin, String password) throws Exception {
        String sql = "SELECT * FROM Admin WHERE IDAdmin = ? AND password = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, IDAdmin);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setIDAdmin(rs.getString("IDAdmin"));
                admin.setPassword(rs.getString("password"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setGender(rs.getString("gender"));
                admin.setPhone(rs.getString("phone"));
                admin.setEmail(rs.getString("email"));
                admin.setAddress(rs.getString("address"));
                admin.setImage(rs.getString("image"));
                admin.setDateOfBirth(rs.getDate("dateOfBirth"));
                return admin;
            }
        }
        return null;
    }

    public Admin getAdminByID(String IDAdmin) throws Exception {
        String sql = "SELECT * FROM Admin WHERE IDAdmin = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, IDAdmin);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setIDAdmin(rs.getString("IDAdmin"));
                admin.setPassword(rs.getString("password"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setGender(rs.getString("gender"));
                admin.setPhone(rs.getString("phone"));
                admin.setEmail(rs.getString("email"));
                admin.setAddress(rs.getString("address"));
                admin.setImage(rs.getString("image"));
                admin.setDateOfBirth(rs.getDate("dateOfBirth"));
                return admin;
            }
        }
        return null;
    }

 
  


 

}
