package DTO;

import java.sql.Date;

public class SavingsCalculation {

    private int idCalculation;
    private String idMember;
    private int cigarettesPerDay;
    private double pricePerPack;
    private int cigarettesPerPack;
    private int daysSinceQuit;
    private double totalSaved;
    private Date calculationDate;

    // Constructors
    public SavingsCalculation() {
    }

    public SavingsCalculation(String idMember, int cigarettesPerDay, double pricePerPack,
            int cigarettesPerPack, int daysSinceQuit, double totalSaved) {
        this.idMember = idMember;
        this.cigarettesPerDay = cigarettesPerDay;
        this.pricePerPack = pricePerPack;
        this.cigarettesPerPack = cigarettesPerPack;
        this.daysSinceQuit = daysSinceQuit;
        this.totalSaved = totalSaved;
    }

    // Getters and Setters
    public int getIdCalculation() {
        return idCalculation;
    }

    public void setIdCalculation(int idCalculation) {
        this.idCalculation = idCalculation;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public int getCigarettesPerDay() {
        return cigarettesPerDay;
    }

    public void setCigarettesPerDay(int cigarettesPerDay) {
        this.cigarettesPerDay = cigarettesPerDay;
    }

    public double getPricePerPack() {
        return pricePerPack;
    }

    public void setPricePerPack(double pricePerPack) {
        this.pricePerPack = pricePerPack;
    }

    public int getCigarettesPerPack() {
        return cigarettesPerPack;
    }

    public void setCigarettesPerPack(int cigarettesPerPack) {
        this.cigarettesPerPack = cigarettesPerPack;
    }

    public int getDaysSinceQuit() {
        return daysSinceQuit;
    }

    public void setDaysSinceQuit(int daysSinceQuit) {
        this.daysSinceQuit = daysSinceQuit;
    }

    public double getTotalSaved() {
        return totalSaved;
    }

    public void setTotalSaved(double totalSaved) {
        this.totalSaved = totalSaved;
    }

    public Date getCalculationDate() {
        return calculationDate;
    }

    public void setCalculationDate(Date calculationDate) {
        this.calculationDate = calculationDate;
    }
}
