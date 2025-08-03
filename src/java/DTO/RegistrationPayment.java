/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author Nghia
 */
public class RegistrationPayment {
     private int IDRegistration;
    private String IDMember;
    private String IDPayment;
    private String IDQuitPlan;
    private String status;
    private Date registerDate;

    public RegistrationPayment(int IDRegistration, String IDMember, String IDPayment, String IDQuitPlan, String status, Date registerDate) {
        this.IDRegistration = IDRegistration;
        this.IDMember = IDMember;
        this.IDPayment = IDPayment;
        this.IDQuitPlan = IDQuitPlan;
        this.status = status;
        this.registerDate = registerDate;
    }

    public int getIDRegistration() {
        return IDRegistration;
    }

    public void setIDRegistration(int IDRegistration) {
        this.IDRegistration = IDRegistration;
    }

    public String getIDMember() {
        return IDMember;
    }

    public void setIDMember(String IDMember) {
        this.IDMember = IDMember;
    }

    public String getIDPayment() {
        return IDPayment;
    }

    public void setIDPayment(String IDPayment) {
        this.IDPayment = IDPayment;
    }

    public String getIDQuitPlan() {
        return IDQuitPlan;
    }

    public void setIDQuitPlan(String IDQuitPlan) {
        this.IDQuitPlan = IDQuitPlan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
    

  
}
