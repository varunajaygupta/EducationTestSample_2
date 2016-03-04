package com.example.novo.educationtestsample.models;

/**
 * Created by Varun Ajay Gupta on 2/3/16.
 */
public class Question {
    String questionNumber;

    public Question(String s) {
        this.questionNumber=s;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }
}
