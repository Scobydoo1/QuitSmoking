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

public class Admin extends Account {

    public Admin() {
    }

    public Admin(String IDAdmin, String password, String adminName, String gender, String phone,
                 String email, String address, String image, Date dateOfBirth, String status) {
        super(IDAdmin, password, adminName, gender, phone, email, address, image, dateOfBirth, status);
    }

    

    public String getIDAdmin() {
        return super.account;
    }

    public void setIDAdmin(String IDAdmin) {
        super.account = IDAdmin;
    }

    public String getPassword() {
        return super.password;
    }

    public void setPassword(String password) {
        super.password = password;
    }

    public String getAdminName() {
        return super.fullname;
    }

    public void setAdminName(String adminName) {
        super.fullname = adminName;
    }

    public String getGender() {
        return super.gender;
    }

    public void setGender(String gender) {
        super.gender = gender;
    }

    public String getPhone() {
        return super.phone;
    }

    public void setPhone(String phone) {
        super.phone = phone;
    }

    public String getEmail() {
        return super.email;
    }

    public void setEmail(String email) {
        super.email = email;
    }

    public String getAddress() {
        return super.address;
    }

    public void setAddress(String address) {
        super.address = address;
    }

    public String getImage() {
        return super.image;
    }

    public void setImage(String image) {
        super.image = image;
    }

    public Date getDateOfBirth() {
        return super.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        super.dateOfBirth = dateOfBirth;
    }
}
