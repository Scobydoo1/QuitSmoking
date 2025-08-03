/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TokenForgotPasswordDTO;
import Utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Thinkpad
 */
public class TokenForgotPasswordDAO implements Serializable{
    public boolean saveToken(TokenForgotPasswordDTO token) throws SQLException, ClassNotFoundException{
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                //write sql statement
                String sql = "Insert into tokenForgotPassword (" 
                        + "IDMember, token, expiryTime"
                        + ") Values("
                        + "?, ?, ? "
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setString(1, token.getIDMember());
                stm.setString(2, token.getToken());
                stm.setTimestamp(3, Timestamp.valueOf(token.getExpiryTime()));
                
                
                //execute
                int effectRows = stm.executeUpdate();
                //proccess
                if (effectRows > 0) {
                    result = true;
                }
                
            }// connection is available
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public TokenForgotPasswordDTO getToken(String token) throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TokenForgotPasswordDTO dto = null;
        try {
            con = DBUtils.getConnection();
            if(con != null){
                String sql = "Select IDMember, expiryTime "
                        + "From tokenForgotPassword "
                        + "Where token = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, token);
                rs = stm.executeQuery();
                if(rs.next()){
                    String IDMember = rs.getString("IDMember");
                    Timestamp expiryTimestamp = rs.getTimestamp("expiryTime");
                    LocalDateTime expiryTime = expiryTimestamp.toLocalDateTime();
                    dto = new TokenForgotPasswordDTO(0, IDMember, token, expiryTime);
                }
            }
        } finally {
            if(stm != null){
                stm.close();
            }
            if(con != null) {
                con.close();
            } 
        }
        return dto;
    }
    
    public boolean deleteToken(String token) throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            if(con != null){
                String sql = "Delete from tokenForgotPassword "
                        + "Where token = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, token);
                int effecRow = stm.executeUpdate();
                if(effecRow > 0){
                    result = true;
                }
            }
        } finally {
            if(stm != null){
                stm.close();
            }
            if(con != null) {
                con.close();
            } 
        }
        return result;
    }
}
