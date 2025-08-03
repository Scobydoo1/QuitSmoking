/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author Nguyen Tien Dat
 */

public class Coach extends Account {

    private String specialization;
    private int experienceYears;

    public Coach() {
    }

    public Coach(String IDCoach, String password, String coachName, String gender, String phone,
                 String email, String address, String image, Date dateOfBirth,
                 String specialization, int experienceYears, String status) {
        super(IDCoach, password, coachName, gender, phone, email, address, image, dateOfBirth, status);
        this.specialization = specialization;
        this.experienceYears = experienceYears;
    }

    public String getIDCoach() {
        return super.account;
    }

    public void setIDCoach(String IDCoach) {
        super.account = IDCoach;
    }

    public String getPassword() {
        return super.password;
    }

    public void setPassword(String password) {
        super.password = password;
    }

    public String getCoachName() {
        return super.fullname;
    }

    public void setCoachName(String coachName) {
        super.fullname = coachName;
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

    public String getStatus() {
        return super.status;
    }

    public void setStatus(String status) {
        super.status = status;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
}