/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author Nghia
 */
public class QuizResult {

    private String IDMember;
    private String IDQuiz;
    private String Answer;
    private Date DateSubmit;

    public QuizResult(String IDMember, String IDQuiz, String Answer, Date DateSubmit) {
        this.IDMember = IDMember;
        this.IDQuiz = IDQuiz;
        this.Answer = Answer;
        this.DateSubmit = DateSubmit;
    }

    public String getIDMember() {
        return IDMember;
    }

    public String getIDQuiz() {
        return IDQuiz;
    }

    public String getAnswer() {
        return Answer;
    }

    public Date getDateSubmit() {
        return DateSubmit;
    }

}
