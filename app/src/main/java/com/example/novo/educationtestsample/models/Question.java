package com.example.novo.educationtestsample.models;

/**
 * Created by Varun on 6/28/2016.
 */
public class Question implements Cloneable
{
    private String question_title;

    private String question_image;

    private String question_type;

    private Answer[] answer_array;

    private String answer_key;

    private String question_marks;

    private int lastCheckedCheckboxPos= -1;

    private Boolean isAttempted= false;


    private Boolean isMarkedForReview=Boolean.FALSE;

    public Boolean getIsMarkedForReview() {
        return isMarkedForReview;
    }

    public void setIsMarkedForReview(Boolean isMarkedForReview) {
        this.isMarkedForReview = isMarkedForReview;
    }

    public int getLastCheckedCheckboxPos() {
        return lastCheckedCheckboxPos;
    }

    public void setLastCheckedCheckboxPos(int lastCheckedCheckboxPos) {
        this.lastCheckedCheckboxPos = lastCheckedCheckboxPos;
    }

    public Boolean getIsAttempted() {
        return isAttempted;
    }

    public void setIsAttempted(Boolean attempted) {
        isAttempted = attempted;
    }

    public String getQuestion_title ()
    {
        return question_title;
    }

    public void setQuestion_title (String question_title)
    {
        this.question_title = question_title;
    }

    public String getQuestion_image ()
    {
        return question_image;
    }

    public void setQuestion_image (String question_image)
    {
        this.question_image = question_image;
    }

    public String getQuestion_type ()
    {
        return question_type;
    }

    public void setQuestion_type (String question_type)
    {
        this.question_type = question_type;
    }

    public Answer[] getAnswer_array ()
    {
        return answer_array;
    }

    public void setAnswer_array (Answer[] answer_array)
    {
        this.answer_array = answer_array;
    }

    public String getAnswer_key ()
    {
        return answer_key;
    }

    public void setAnswer_key (String answer_key)
    {
        this.answer_key = answer_key;
    }

    public String getQuestion_marks ()
    {
        return question_marks;
    }

    public void setQuestion_marks (String question_marks)
    {
        this.question_marks = question_marks;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [question_title = "+question_title+", question_image = "+question_image+", question_type = "+question_type+", answer_array = "+answer_array+", answer_key = "+answer_key+", question_marks = "+question_marks+"]";
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
