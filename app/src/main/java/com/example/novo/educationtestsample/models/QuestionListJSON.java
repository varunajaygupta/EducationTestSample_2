package com.example.novo.educationtestsample.models;

import java.util.List;

/**
 * Created by Varun Ajay Gupta on 5/3/16.
 */
public class QuestionListJSON implements Cloneable {
    public static QuestionListJSON mquestionList=null;
    List<Question> questionList;
    int currentQuestion=0;

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

}
