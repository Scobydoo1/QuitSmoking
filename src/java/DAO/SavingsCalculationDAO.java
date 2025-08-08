package DAO;

import DTO.SavingsCalculation;
import Utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SavingsCalculationDAO {

    public boolean saveSavingsCalculation(SavingsCalculation calc) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO SavingsCalculation (IDMember, cigarettesPerDay, pricePerPack, cigarettesPerPack, daysSinceQuit, totalSaved, calculationDate) VALUES (?, ?, ?, ?, ?, ?, GETDATE())";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, calc.getIdMember());
            ps.setInt(2, calc.getCigarettesPerDay());
            ps.setDouble(3, calc.getPricePerPack());
            ps.setInt(4, calc.getCigarettesPerPack());
            ps.setInt(5, calc.getDaysSinceQuit());
            ps.setDouble(6, calc.getTotalSaved());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public List<SavingsCalculation> getSavingsHistoryByMember(String idMember) throws SQLException, ClassNotFoundException {
        List<SavingsCalculation> history = new ArrayList<>();
        String sql = "SELECT * FROM SavingsCalculation WHERE IDMember = ? ORDER BY calculationDate DESC";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idMember);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SavingsCalculation calc = new SavingsCalculation();
                calc.setIdCalculation(rs.getInt("IDCalculation"));
                calc.setIdMember(rs.getString("IDMember"));
                calc.setCigarettesPerDay(rs.getInt("cigarettesPerDay"));
                calc.setPricePerPack(rs.getDouble("pricePerPack"));
                calc.setCigarettesPerPack(rs.getInt("cigarettesPerPack"));
                calc.setDaysSinceQuit(rs.getInt("daysSinceQuit"));
                calc.setTotalSaved(rs.getDouble("totalSaved"));
                calc.setCalculationDate(rs.getDate("calculationDate"));
                history.add(calc);
            }
        }
        return history;
    }

    public double calculateSavings(int cigarettesPerDay, double pricePerPack, int cigarettesPerPack, int daysSinceQuit) {
        double packsPerDay = (double) cigarettesPerDay / cigarettesPerPack;
        return packsPerDay * pricePerPack * daysSinceQuit;
    }
}
