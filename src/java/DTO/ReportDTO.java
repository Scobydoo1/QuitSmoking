/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nguyen Tien Dat
 */
public class ReportDTO {
    private int reportID;
    private String reporterID;
    private String role;
    private String reportType;
    private String link;
    private String description;

    public ReportDTO() {}

    public ReportDTO(String reporterID, String role, String reportType, String link, String description) {
        this.reporterID = reporterID;
        this.role = role;
        this.reportType = reportType;
        this.link = link;
        this.description = description;
    }

    // Getters và Setters
    public int getReportID() { return reportID; }
    public void setReportID(int reportID) { this.reportID = reportID; }

    public String getReporterID() { return reporterID; }
    public void setReporterID(String reporterID) { this.reporterID = reporterID; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
