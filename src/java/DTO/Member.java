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

public class Member extends Account {

    private Date joinDate;
    private int point;
    private String IDCoach;
    private String subscription;

    public Member() {
    }

    public Member(String IDMember, String password, String memberName, String gender, String phone,
                  String email, String address, Date dateOfBirth, Date joinDate, String image,
                  int point, String IDCoach, String subscription, String status) {
        super(IDMember, password, memberName, gender, phone, email, address, image, dateOfBirth, status);
        this.joinDate = joinDate;
        this.point = point;
        this.IDCoach = IDCoach;
        this.subscription = subscription;
    }

    public String getIDMember() {
        return super.account;
    }

    public void setIDMember(String IDMember) {
        super.account = IDMember;
    }

    public String getPassword() {
        return super.password;
    }

    public void setPassword(String password) {
        super.password = password;
    }

    public String getMemberName() {
        return super.fullname;
    }

    public void setMemberName(String memberName) {
        super.fullname = memberName;
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

    public Date getDateOfBirth() {
        return super.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        super.dateOfBirth = dateOfBirth;
    }

    public String getImage() {
        return super.image;
    }

    public void setImage(String image) {
        super.image = image;
    }

    public String getStatus() {
        return super.status;
    }

    public void setStatus(String status) {
        super.status = status;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getIDCoach() {
        return IDCoach;
    }

    public void setIDCoach(String IDCoach) {
        this.IDCoach = IDCoach;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }
}
