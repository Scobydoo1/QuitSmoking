package DAO;

import DTO.ProgressLog;
import Utils.DBUtils;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgressLogDAO {

    public ProgressLog getById(int idLog) throws Exception {
        Connection conn = DBUtils.getConnection();
        String sql = "SELECT * FROM ProgressLog WHERE IDLog = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idLog);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            ProgressLog log = new ProgressLog();
            log.setIdLog(rs.getInt("IDLog"));
            log.setIdMember(rs.getString("IDMember"));
            log.setIdCoach(rs.getString("IDCoach"));
            log.setStartDate(rs.getDate("StartDate"));
            log.setEndDate(rs.getDate("EndDate"));
            log.setType(rs.getString("Type"));
            log.setProgress(rs.getString("progress"));

            for (int i = 1; i <= 5; i++) {
                String q = rs.getString("qs" + i);
                log.getClass().getMethod("setQs" + i, String.class).invoke(log, q);
                String a = rs.getString("as" + i);
                log.setAnswer(i, a);
            }

            log.setPoint(rs.getInt("point"));
            log.setStatus(rs.getString("status"));

            return log;
        }
        return null;
    }

    private static final String INSERT_SQL =
            "INSERT INTO ProgressLog " +
            "(IDMember, IDCoach, StartDate, EndDate, progress, " +
            "qs1, qs2, qs3, qs4, qs5, " +
            "type, status) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    public static void insertQuestions(String idMember, String idCoach,
                                       Date endDate, String progress, String[] questions, String type) throws ClassNotFoundException {
        String[] qs = new String[5];
        for (int i = 0; i < 5; i++) {
            if (questions != null && i < questions.length) {
                String raw = questions[i];
                qs[i] = (raw != null && !raw.trim().isEmpty()) ? raw.trim() : null;
            } else {
                qs[i] = null;
            }
        }

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            int idx = 1;
            ps.setString(idx++, idMember);
            ps.setString(idx++, idCoach);
            ps.setDate(idx++, new Date(System.currentTimeMillis()));
            ps.setDate(idx++, endDate);
            ps.setString(idx++, progress);

            for (int i = 0; i < 5; i++) {
                if (qs[i] != null) {
                    ps.setString(idx++, qs[i]);
                } else {
                    ps.setNull(idx++, java.sql.Types.NVARCHAR);
                }
            }

            ps.setString(idx++, type);
            ps.setString(idx++, "save");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting ProgressLog for member=" + idMember, e);
        }
    }

    public List<ProgressLog> getProgressLogsByMember(String idMember) throws SQLException, ClassNotFoundException {
        List<ProgressLog> progressLogs = new ArrayList<>();

        String sql = "SELECT * FROM ProgressLog WHERE IDMember = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idMember);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idLog = rs.getInt("IDLog");
                String idCoach = rs.getString("IDCoach");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                String type = rs.getString("type");
                String progress = rs.getString("progress");

                String qs1 = rs.getString("qs1");
                String qs2 = rs.getString("qs2");
                String qs3 = rs.getString("qs3");
                String qs4 = rs.getString("qs4");
                String qs5 = rs.getString("qs5");

                String as1 = rs.getString("as1");
                String as2 = rs.getString("as2");
                String as3 = rs.getString("as3");
                String as4 = rs.getString("as4");
                String as5 = rs.getString("as5");

                int point = rs.getInt("point");
                String status = rs.getString("status");

                ProgressLog log = new ProgressLog(
                        idLog, idMember, idCoach, startDate, endDate, type, progress,
                        qs1, qs2, qs3, qs4, qs5,
                        as1, as2, as3, as4, as5,
                        point, status
                );

                progressLogs.add(log);
            }
        }

        return progressLogs;
    }

    public static boolean update(ProgressLog log) throws Exception {
        String sql =
                "UPDATE ProgressLog SET " +
                        "IDMember = ?, IDCoach = ?, StartDate = ?, EndDate = ?, type = ?, progress = ?, " +
                        "qs1 = ?, qs2 = ?, qs3 = ?, qs4 = ?, qs5 = ?, " +
                        "as1 = ?, as2 = ?, as3 = ?, as4 = ?, as5 = ?, " +
                        "point = ?, status = ? " +
                        "WHERE IDLog = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, log.getIdMember());
            stmt.setString(2, log.getIdCoach());
            stmt.setDate(3, log.getStartDate());
            stmt.setDate(4, log.getEndDate());
            stmt.setString(5, log.getType());
            stmt.setString(6, log.getProgress());

            stmt.setString(7, log.getQs1());
            stmt.setString(8, log.getQs2());
            stmt.setString(9, log.getQs3());
            stmt.setString(10, log.getQs4());
            stmt.setString(11, log.getQs5());

            stmt.setString(12, log.getAs1());
            stmt.setString(13, log.getAs2());
            stmt.setString(14, log.getAs3());
            stmt.setString(15, log.getAs4());
            stmt.setString(16, log.getAs5());

            stmt.setInt(17, log.getPoint());
            stmt.setString(18, log.getStatus());
            stmt.setInt(19, log.getIdLog());

            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<ProgressLog> getLogsOfMemberForCoach(String idMember, String idCoach) throws Exception {
    List<ProgressLog> list = new ArrayList<>();
    String sql = "SELECT * FROM ProgressLog WHERE IDMember = ? AND IDCoach = ?";
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, idMember);
        ps.setString(2, idCoach);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ProgressLog log = new ProgressLog(
                rs.getInt("IDLog"), idMember, idCoach,
                rs.getDate("StartDate"), rs.getDate("EndDate"),
                rs.getString("type"), rs.getString("progress"),
                rs.getString("qs1"), rs.getString("qs2"), rs.getString("qs3"),
                rs.getString("qs4"), rs.getString("qs5"),
                rs.getString("as1"), rs.getString("as2"), rs.getString("as3"),
                rs.getString("as4"), rs.getString("as5"),
                rs.getInt("point"), rs.getString("status")
            );
            list.add(log);
        }
    }
    return list;
}

} 
