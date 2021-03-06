package com.example.novo.educationtestsample.models;

/**
 * Created by Varun on 6/28/2016.
 */
public class Answer
{
    private String answer_image;

    private Boolean answer_marked;

    private String option_number;

    private String answer_title;

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

    public String getOption_number ()
    {
        return option_number;
    }

    public void setOption_number (String option_number)
    {
        this.option_number = option_number;
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
        return "ClassPojo [answer_image = "+answer_image+", answer_marked = "+answer_marked+", option_number = "+option_number+", answer_title = "+answer_title+"]";
    }
}
