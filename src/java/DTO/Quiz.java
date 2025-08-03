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
public class Quiz {
   
    private String IDQuiz;   // VARCHAR(10), khóa chính
    private String Question; // Ntext
    private String AnswerA;  // Ntext
    private String AnswerB;  // Ntext
    private String AnswerC;  // Ntext
    private String AnswerD;  // Ntext

    // Constructor không tham số
    public Quiz() {
    }

    // Constructor đầy đủ tham số
    public Quiz(String IDQuiz, String question, String answerA, String answerB, String answerC, String answerD) {
        this.IDQuiz = IDQuiz;
        this.Question = question;
        this.AnswerA = answerA;
        this.AnswerB = answerB;
        this.AnswerC = answerC;
        this.AnswerD = answerD;
    }

    // Getter và Setter
    public String getIDQuiz() {
        return IDQuiz;
    }

    public void setIDQuiz(String IDQuiz) {
        this.IDQuiz = IDQuiz;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        this.Question = question;
    }

    public String getAnswerA() {
        return AnswerA;
    }

    public void setAnswerA(String answerA) {
        this.AnswerA = answerA;
    }

    public String getAnswerB() {
        return AnswerB;
    }

    public void setAnswerB(String answerB) {
        this.AnswerB = answerB;
    }

    public String getAnswerC() {
        return AnswerC;
    }

    public void setAnswerC(String answerC) {
        this.AnswerC = answerC;
    }

    public String getAnswerD() {
        return AnswerD;
    }

    public void setAnswerD(String answerD) {
        this.AnswerD = answerD;
    }
    
}
