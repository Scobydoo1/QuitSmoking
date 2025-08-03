/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nghia
 */
public class QuitPlan {

    private String idQuitPlan;
    private int periodOfTime;
    private String goals;
    private String progress;
    private double price;
    private String status;

  
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    // Getters và setters
    public String getIdQuitPlan() {
        return idQuitPlan;
    }

    public void setIdQuitPlan(String idQuitPlan) {
        this.idQuitPlan = idQuitPlan;
    }

    public int getPeriodOfTime() {
        return periodOfTime;
    }

    public void setPeriodOfTime(int periodOfTime) {
        this.periodOfTime = periodOfTime;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
