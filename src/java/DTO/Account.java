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

public class Account {
    protected String account;
    protected String password;
    protected String fullname;
    protected String gender;
    protected String phone;
    protected String email;
    protected String address;
    protected String image;
    protected Date dateOfBirth;
    protected String status;
    public Account() {
    }

    public Account(String account, String password, String fullname, String gender, String phone,
                   String email, String address, String image, Date dateOfBirth, String status) {
        this.account = account;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.image = image;
        this.dateOfBirth = dateOfBirth;
        this.status= status;
    }

    
}
