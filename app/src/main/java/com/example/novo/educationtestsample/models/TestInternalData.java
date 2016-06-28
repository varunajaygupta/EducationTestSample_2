package com.example.novo.educationtestsample.models;

public class TestInternalData
{
    private String id;

    private String coaching_id;

    private String[] topics;

    private String title;

    private String status;

    private String duration_seconds;

    private String description;

    private String date_updated;

    private String[] batches;

    private Question[] questions;

    private String teacher_id;

    private String window_stop;

    private String window_start;

    private String test_id;

    private String total_marks;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCoaching_id ()
    {
        return coaching_id;
    }

    public void setCoaching_id (String coaching_id)
    {
        this.coaching_id = coaching_id;
    }

    public String[] getTopics ()
    {
        return topics;
    }

    public void setTopics (String[] topics)
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

    public String getDuration_seconds ()
    {
        return duration_seconds;
    }

    public void setDuration_seconds (String duration_seconds)
    {
        this.duration_seconds = duration_seconds;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getDate_updated ()
    {
        return date_updated;
    }

    public void setDate_updated (String date_updated)
    {
        this.date_updated = date_updated;
    }

    public String[] getBatches ()
    {
        return batches;
    }

    public void setBatches (String[] batches)
    {
        this.batches = batches;
    }

    public Question[] getQuestions ()
    {
        return questions;
    }

    public void setQuestions (Question[] questions)
    {
        this.questions = questions;
    }

    public String getTeacher_id ()
    {
        return teacher_id;
    }

    public void setTeacher_id (String teacher_id)
    {
        this.teacher_id = teacher_id;
    }

    public String getWindow_stop ()
    {
        return window_stop;
    }

    public void setWindow_stop (String window_stop)
    {
        this.window_stop = window_stop;
    }

    public String getWindow_start ()
    {
        return window_start;
    }

    public void setWindow_start (String window_start)
    {
        this.window_start = window_start;
    }

    public String getTest_id ()
    {
        return test_id;
    }

    public void setTest_id (String test_id)
    {
        this.test_id = test_id;
    }

    public String getTotal_marks ()
    {
        return total_marks;
    }

    public void setTotal_marks (String total_marks)
    {
        this.total_marks = total_marks;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", coaching_id = "+coaching_id+", topics = "+topics+", title = "+title+", status = "+status+", duration_seconds = "+duration_seconds+", description = "+description+", date_updated = "+date_updated+", batches = "+batches+", questions = "+questions+", teacher_id = "+teacher_id+", window_stop = "+window_stop+", window_start = "+window_start+", test_id = "+test_id+", total_marks = "+total_marks+"]";
    }
}