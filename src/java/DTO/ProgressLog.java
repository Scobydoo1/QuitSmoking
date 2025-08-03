package DTO;

import java.sql.Date;

public class ProgressLog {
    private int idLog;
    private String idMember;
    private String idCoach;
    private Date startDate;
    private Date endDate;
    private String type;
    private String progress;

    private String qs1, qs2, qs3, qs4, qs5;
    private String as1, as2, as3, as4, as5;

    private int point;
    private String status;

    public ProgressLog() {
    }

    public ProgressLog(int idLog, String idMember, String idCoach, Date startDate, Date endDate, String type,
                       String progress,
                       String qs1, String qs2, String qs3, String qs4, String qs5,
                       String as1, String as2, String as3, String as4, String as5,
                       int point, String status) {
        this.idLog = idLog;
        this.idMember = idMember;
        this.idCoach = idCoach;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.progress = progress;
        this.qs1 = qs1;
        this.qs2 = qs2;
        this.qs3 = qs3;
        this.qs4 = qs4;
        this.qs5 = qs5;
        this.as1 = as1;
        this.as2 = as2;
        this.as3 = as3;
        this.as4 = as4;
        this.as5 = as5;
        this.point = point;
        this.status = status;
    }

    // Getters and setters

    public int getIdLog() { return idLog; }
    public void setIdLog(int idLog) { this.idLog = idLog; }

    public String getIdMember() { return idMember; }
    public void setIdMember(String idMember) { this.idMember = idMember; }

    public String getIdCoach() { return idCoach; }
    public void setIdCoach(String idCoach) { this.idCoach = idCoach; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getProgress() { return progress; }
    public void setProgress(String progress) { this.progress = progress; }

    public String getQs1() { return qs1; }
    public void setQs1(String qs1) { this.qs1 = qs1; }
    public String getQs2() { return qs2; }
    public void setQs2(String qs2) { this.qs2 = qs2; }
    public String getQs3() { return qs3; }
    public void setQs3(String qs3) { this.qs3 = qs3; }
    public String getQs4() { return qs4; }
    public void setQs4(String qs4) { this.qs4 = qs4; }
    public String getQs5() { return qs5; }
    public void setQs5(String qs5) { this.qs5 = qs5; }

    public String getAs1() { return as1; }
    public void setAs1(String as1) { this.as1 = as1; }
    public String getAs2() { return as2; }
    public void setAs2(String as2) { this.as2 = as2; }
    public String getAs3() { return as3; }
    public void setAs3(String as3) { this.as3 = as3; }
    public String getAs4() { return as4; }
    public void setAs4(String as4) { this.as4 = as4; }
    public String getAs5() { return as5; }
    public void setAs5(String as5) { this.as5 = as5; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

   public void setAnswer(int i, String answer) {
    switch (i) {
        case 1:
            setAs1(answer);
            break;
        case 2:
            setAs2(answer);
            break;
        case 3:
            setAs3(answer);
            break;
        case 4:
            setAs4(answer);
            break;
        case 5:
            setAs5(answer);
            break;
    }
}

public String getAnswer(int i) {
    switch (i) {
        case 1:
            return getAs1();
        case 2:
            return getAs2();
        case 3:
            return getAs3();
        case 4:
            return getAs4();
        case 5:
            return getAs5();
        default:
            return null;
    }
}

} 
