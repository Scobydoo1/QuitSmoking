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
public class MemberBadgeRankingDTO {
    private String idMember;
    private String memberName;
    private String status;
    private int badgeCount;

    public MemberBadgeRankingDTO() {
    }

    public MemberBadgeRankingDTO(String idMember, String memberName, String status, int badgeCount) {
        this.idMember = idMember;
        this.memberName = memberName;
        this.status = status;
        this.badgeCount = badgeCount;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBadgeCount() {
        return badgeCount;
    }

    public void setBadgeCount(int badgeCount) {
        this.badgeCount = badgeCount;
    }
    
    
}
