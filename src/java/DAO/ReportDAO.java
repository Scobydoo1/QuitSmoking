/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ReportDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Tien Dat
 */
public class ReportDAO {
    public boolean insertReport(ReportDTO report) throws ClassNotFoundException {
        String sql = "INSERT INTO Report (ReporterID, Role, ReportType, link, Description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, report.getReporterID());
            stmt.setString(2, report.getRole());
            stmt.setString(3, report.getReportType());
            stmt.setString(4, report.getLink());
            stmt.setString(5, report.getDescription());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<ReportDTO> getAllReports() {
        List<ReportDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Report ORDER BY ReportID DESC";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ReportDTO report = new ReportDTO();
                report.setReportID(rs.getInt("ReportID"));
                report.setReporterID(rs.getString("ReporterID"));
                report.setRole(rs.getString("Role"));
                report.setReportType(rs.getString("ReportType"));
                report.setLink(rs.getString("link"));
                report.setDescription(rs.getString("Description"));
                list.add(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
