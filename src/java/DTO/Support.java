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


import java.sql.Date;

public class Support {
    private int idSupport;
    private String idMember;
    private String idCoach;
    private String authorSend; // "member" hoặc "coach"
    private Date feedbackDate;
    private String content;

    public Support() {}

    public Support(int idSupport, String idMember, String idCoach, String authorSend, Date feedbackDate, String content) {
        this.idSupport = idSupport;
        this.idMember = idMember;
        this.idCoach = idCoach;
        this.authorSend = authorSend;
        this.feedbackDate = feedbackDate;
        this.content = content;
    }

    public int getIdSupport() {
        return idSupport;
    }

    public void setIdSupport(int idSupport) {
        this.idSupport = idSupport;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(String idCoach) {
        this.idCoach = idCoach;
    }

    public String getAuthorSend() {
        return authorSend;
    }

    public void setAuthorSend(String authorSend) {
        this.authorSend = authorSend;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
