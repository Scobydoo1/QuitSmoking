/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nghia
 */
public class Payment {
     private String idPayment;   // ID phương thức thanh toán
    private String method;      // Tên phương thức thanh toán (Momo, VNPay, ZaloPay...)
    private String logo;        // Đường dẫn logo phương thức thanh toán

    // Constructor
    public Payment(String idPayment, String method, String logo) {
        this.idPayment = idPayment;
        this.method = method;
        this.logo = logo;
    }

    // Getters and Setters
    public String getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(String idPayment) {
        this.idPayment = idPayment;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
