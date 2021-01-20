package com.example.asd.imtired.model;

public class QuestionScore {

    private String questionScore;
    private String user;
    private String score;

    public QuestionScore() {
    }

    public QuestionScore(String questionScore, String user, String score) {
        this.questionScore = questionScore;
        this.user = user;
        this.score = score;
    }

    public String getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(String questionScore) {
        this.questionScore = questionScore;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
