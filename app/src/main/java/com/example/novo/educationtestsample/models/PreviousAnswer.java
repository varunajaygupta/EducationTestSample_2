package com.example.novo.educationtestsample.models;

/**
 * Created by Varun Ajay Gupta on 5/3/16.
 */
public class PreviousAnswer
{
    private String answer_image;

    private Boolean answer_marked;

    private String answer_title;


    public PreviousAnswer(PreviousAnswer previousAnswer) {
       this.answer_image= previousAnswer.answer_image;
        this.answer_marked= previousAnswer.answer_marked;
        this.answer_title= previousAnswer.answer_title;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getAnswer_image ()
    {
        return answer_image;
    }

    public void setAnswer_image (String answer_image)
    {
        this.answer_image = answer_image;
    }

    public Boolean getAnswer_marked ()
    {
        return answer_marked;
    }

    public void setAnswer_marked (Boolean answer_marked)
    {
        this.answer_marked = answer_marked;
    }

    public String getAnswer_title ()
    {
        return answer_title;
    }

    public void setAnswer_title (String answer_title)
    {
        this.answer_title = answer_title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [answer_image = "+answer_image+", answer_marked = "+answer_marked+", answer_title = "+answer_title+"]";
    }
}