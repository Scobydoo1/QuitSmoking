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
import java.sql.Time;
import java.sql.Date;

public class Schedule {
    private String IDSchedule;
    private String IDCoach;
    private String IDMember;
    private Date sessionDate;
    private Time startTime;
    private Time endTime;
    private String status;
    private String detail;
    private String meetLink;

    // Thông tin bổ sung cho giao diện (không map trực tiếp từ DB)
    private String memberName;

    public Schedule() {}

    public Schedule(String IDSchedule, String IDCoach, String IDMember, Date sessionDate, Time startTime, Time endTime, String status, String detail, String meetLink) {
        this.IDSchedule = IDSchedule;
        this.IDCoach = IDCoach;
        this.IDMember = IDMember;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.detail = detail;
        this.meetLink = meetLink;
    }

    public String getIDSchedule() {
        return IDSchedule;
    }

    public void setIDSchedule(String IDSchedule) {
        this.IDSchedule = IDSchedule;
    }

    public String getIDCoach() {
        return IDCoach;
    }

    public void setIDCoach(String IDCoach) {
        this.IDCoach = IDCoach;
    }

    public String getIDMember() {
        return IDMember;
    }

    public void setIDMember(String IDMember) {
        this.IDMember = IDMember;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMeetLink() {
        return meetLink;
    }

    public void setMeetLink(String meetLink) {
        this.meetLink = meetLink;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
