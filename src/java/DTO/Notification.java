/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nguyen Tien Dat
 */
public class Notification {
    private String idNotification;
    private String type;
    private String message;
    private Date date;
    private boolean isRead;

    // Constructors
    public Notification() {}
    
    public Notification(String idNotification, String type, String message, Date date, boolean isRead) {
        this.idNotification = idNotification;
        this.type = type;
        this.message = message;
        this.date = date;
        this.isRead = isRead;
    }

    // Getters và Setters
    public String getIdNotification() {
        return idNotification;
    }
    
    public void setIdNotification(String idNotification) {
        this.idNotification = idNotification;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public boolean isRead() {
        return isRead;
    }
    
    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
    
    // Utility methods cho JSP
    public String getFormattedDate() {
        if (date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(date);
    }
    
    public String getTypeIcon() {
        if (type == null) return "fas fa-info-circle";
        
        String lowerType = type.toLowerCase();
        if ("success".equals(lowerType)) {
            return "fas fa-check-circle";
        } else if ("warning".equals(lowerType)) {
            return "fas fa-exclamation-triangle";
        } else if ("reminder".equals(lowerType)) {
            return "fas fa-clock";
        } else {
            return "fas fa-info-circle";
        }
    }
    
    public String getTypeColor() {
        if (type == null) return "#3498db";
        
        String lowerType = type.toLowerCase();
        if ("success".equals(lowerType)) {
            return "#27ae60";
        } else if ("warning".equals(lowerType)) {
            return "#e74c3c";
        } else if ("reminder".equals(lowerType)) {
            return "#f39c12";
        } else {
            return "#3498db";
        }
    }
    
    public String toString() {
        return "Notification{" +
                "idNotification='" + idNotification + '\'' +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", isRead=" + isRead +
                '}';
    }
}