/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Thinkpad
 */
public class TokenForgotPasswordDTO implements Serializable{
    private int id;
    private String IDMember;
    private String token;
    private LocalDateTime expiryTime;

    public TokenForgotPasswordDTO() {
    }

    public TokenForgotPasswordDTO(int id, String IDMember, String token, LocalDateTime expiryTime) {
        this.id = id;
        this.IDMember = IDMember;
        this.token = token;
        this.expiryTime = expiryTime;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the IDMember
     */
    public String getIDMember() {
        return IDMember;
    }

    /**
     * @param IDMember the IDMember to set
     */
    public void setIDMember(String IDMember) {
        this.IDMember = IDMember;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the expiryTime
     */
    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    /**
     * @param expiryTime the expiryTime to set
     */
    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
    
    
}
