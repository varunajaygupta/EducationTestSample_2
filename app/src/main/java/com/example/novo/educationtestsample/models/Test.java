package com.example.novo.educationtestsample.models;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Test
{
    private String id;

    private String duration_mins;

    private String coaching_id;

    private ArrayList<String> topics;

    private String title;

    private String status;

    private String description;

    private ArrayList<String> batches;

  //  private ArrayList<Question> questions;

    private String test_id;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDuration_mins ()
    {
        return duration_mins;
    }

    public void setDuration_mins (String duration_mins)
    {
        this.duration_mins = duration_mins;
    }

    public String getCoaching_id ()
    {
        return coaching_id;
    }

    public void setCoaching_id (String coaching_id)
    {
        this.coaching_id = coaching_id;
    }

    public ArrayList<String> getTopics ()
    {
        return topics;
    }

    public void setTopics (ArrayList<String> topics)
    {
        this.topics = topics;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public ArrayList<String> getBatches ()
    {
        return batches;
    }

    public void setBatches (ArrayList<String> batches)
    {
        this.batches = batches;
    }

//    public ArrayList<Question> getQuestions ()
//    {
//        return questions;
//    }

//    public void setQuestions (ArrayList<Question> questions)
//    {
//        this.questions = questions;
//    }

    public String getTest_id ()
    {
        return test_id;
    }

    public void setTest_id (String test_id)
    {
        this.test_id = test_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", duration_mins = "+duration_mins+", coaching_id = "+coaching_id+", topics = "+topics+", title = "+title+", status = "+status+", description = "+description+", batches = "+batches+", test_id = "+test_id+"]";
    }
}