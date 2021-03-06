package com.example.novo.educationtestsample.models;

import java.util.List;

/**
 * Created by Varun Ajay Gupta on 5/3/16.
 */
public class QuestionListJSON implements Cloneable {
    public static QuestionListJSON mquestionList=null;
    List<Question> questionList;
    int currentQuestion;
    String testId;
    String testDuration;
    int noOfQuesAttempted=0;
    String timeLeft;
    String student_name;
    String testStatus="";

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getNoOfQuesAttempted() {
        return noOfQuesAttempted;
    }

    public void setNoOfQuesAttempted(int noOfQuesAttempted) {
        this.noOfQuesAttempted = noOfQuesAttempted;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(String testDuration) {
        this.testDuration = testDuration;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    private QuestionListJSON(){

    }
    public static QuestionListJSON getInstance(){
        if(mquestionList==null){
            mquestionList=new QuestionListJSON();
        }
        return mquestionList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void setQuestionListJSONObjectToNULL(){
        setTestDuration(null);
        setTestId(null);
        setCurrentQuestion(0);
        setNoOfQuesAttempted(0);
        setTestStatus("");
        setQuestionList(null);
    }
}
